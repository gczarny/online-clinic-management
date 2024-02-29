package pl.online_clinic_management.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.online_clinic_management.api.dto.*;
import pl.online_clinic_management.api.dto.mapper.*;
import pl.online_clinic_management.business.*;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.DoctorAvailability;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.infrastructure.security.OnlineClinicManagementUserDetailsService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PatientController {

    private static final String PATIENT = "/patient";
    private static final String PATIENT_APPOINTMENT = "/patient/patient_appointment";
    private static final String PATIENT_APPOINTMENT_INFO = "/patient_appointment_notes";
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final OnlineClinicManagementUserDetailsService userService;
    private final DoctorService doctorService;
    private final SpecialtyService specialtyService;
    private final DoctorAvailabilityService doctorAvailabilityService;

    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;
    private final SpecialtyMapper specialtyMapper;
    private final DoctorMapper doctorMapper;
    private final DoctorAvailabilityMapper doctorAvailabilityMapper;

    @GetMapping(value = PATIENT)
    public String homePage(Principal principal, Model model) {
        Patient patient = getPatient(principal);
        PatientDTO patientDTO = patientMapper.map(patient);

        model.addAttribute("patientDTO", patientDTO);
        return "patient_portal";
    }

    @GetMapping(value = PATIENT_APPOINTMENT)
    public ModelAndView appointmentPage(Principal principal, Model model) {
        Patient patient = getPatient(principal);
        Map<String, ?> data = prepareNecessaryData(patient);
        return new ModelAndView("patient_appointment", data);
//        model.addAttribute("appointmentsDTO", appointments);
//        model.addAttribute("specialtiesDTO", specialtiesDTO);
//        model.addAttribute("specialtyToDoctors", specialtyToDoctors);
//        model.addAttribute("appointmentDTO", new AppointmentDTO());
//        return "patient_appointment";
    }

    @PostMapping(value = PATIENT_APPOINTMENT)
    public String makeAppointment(
            @Valid @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO,
            BindingResult result,
            ModelMap model
    ) {
        if (result.hasErrors()) {
            return "patient_appointment"; // or whatever your view name is
        }


        Appointment appointment = appointmentMapper.map(appointmentDTO);
        log.info("Appointment: {}", appointment);
        //appointmentService.save(appointment);
        return "redirect:/patient/patient_appointment";

    }

    private Patient getPatient(Principal principal) {
        ClinicUser clinicUser = userService.findByUserName(principal.getName());
        Patient patient = patientService.getPatientInfo(clinicUser.getId());
        return patient;
    }

    private Map<String, ?> prepareNecessaryData(Patient patient) {
        List<AppointmentDTO> appointmentsDTO = appointmentService.findByPatientId(patient.getPatientId()).stream()
                .map(appointmentMapper::map)
                .toList();
        List<SpecialtyDTO> specialtiesDTO = specialtyService.findAll().stream()
                .map(specialtyMapper::map)
                .toList();
        List<DoctorDTO> doctorsDTO = doctorService.findAll().stream()
                .map(doctorMapper::map)
                .toList();

        Map<String, List<DoctorDTO>> specialtyToDoctors = new HashMap<>();
        doctorsDTO.forEach(doctorDTO -> doctorDTO.getSpecialties()
                .forEach(specialtyDTO -> specialtyToDoctors
                        .computeIfAbsent(specialtyDTO.getName(), k -> new ArrayList<>())
                        .add(doctorDTO)
                )
        );

        Map<Long, List<DoctorAvailabilityDTO>> doctorToAvailabilities = new HashMap<>();

        doctorsDTO.forEach(doctorDTO -> {
            List<DoctorAvailability> availabilities = doctorAvailabilityService.findAvailableTimesForDoctor(doctorDTO.getDoctorId());
            List<DoctorAvailabilityDTO> availabilityDTOS = availabilities.stream()
                    .map(doctorAvailabilityMapper::map)
                    .collect(Collectors.toList());
            doctorToAvailabilities.put(doctorDTO.getDoctorId(), availabilityDTOS);
        });
        log.info("Doctor to availabilities: {}", doctorToAvailabilities);
        Map<Long, DoctorDTO> doctorDTOMap = doctorsDTO.stream()
                .collect(Collectors.toMap(DoctorDTO::getDoctorId, doctorDTO -> doctorDTO));
        log.info("Doctor DTO map: {}", doctorDTOMap);
        return Map.of(
                "appointmentsDTO", appointmentsDTO,
                "specialtiesDTO", specialtiesDTO,
                "specialtyToDoctors", specialtyToDoctors,
                "appointmentDTO", AppointmentDTO.builder().appointmentDate(OffsetDateTime.now()).build(),
                "doctorToAvailabilities", doctorToAvailabilities,
                "doctorDTOMap", doctorDTOMap
        );
    }
}