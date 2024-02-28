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
import pl.online_clinic_management.api.dto.AppointmentDTO;
import pl.online_clinic_management.api.dto.DoctorDTO;
import pl.online_clinic_management.api.dto.PatientDTO;
import pl.online_clinic_management.api.dto.SpecialtyDTO;
import pl.online_clinic_management.api.dto.mapper.AppointmentMapper;
import pl.online_clinic_management.api.dto.mapper.DoctorMapper;
import pl.online_clinic_management.api.dto.mapper.PatientMapper;
import pl.online_clinic_management.api.dto.mapper.SpecialtyMapper;
import pl.online_clinic_management.business.AppointmentService;
import pl.online_clinic_management.business.DoctorService;
import pl.online_clinic_management.business.PatientService;
import pl.online_clinic_management.business.SpecialtyService;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.infrastructure.security.OnlineClinicManagementUserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.Map;
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

    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;
    private final SpecialtyMapper specialtyMapper;
    private final DoctorMapper doctorMapper;

    @GetMapping(value = PATIENT)
    public String homePage(Principal principal, Model model) {
        Patient patient = getPatient(principal);
        PatientDTO patientDTO = patientMapper.map(patient);

        model.addAttribute("patientDTO", patientDTO);
        return "patient_portal";
    }

    @GetMapping(value = PATIENT_APPOINTMENT)
    public String appointmentPage(Principal principal, Model model) {
        Patient patient = getPatient(principal);
        List<AppointmentDTO> appointments = appointmentService.findByPatientId(patient.getPatientId()).stream()
                .map(appointmentMapper::map)
                .toList();

        model.addAttribute("appointmentsDTO", appointments);
        model.addAttribute("appointmentDTO", new AppointmentDTO());
        return "patient_appointment";
    }

//    @PostMapping(value = PATIENT_APPOINTMENT)
//    public String makeAppointment(
//            @Valid @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO,
//            BindingResult result,
//            ModelMap model
//    ){
//        List<SpecialtyDTO> specialtiesDTO = specialtyService.findAll().stream()
//                .map(specialtyMapper::map)
//                .toList();
//        log.info("Specialties: {}", specialtiesDTO);
//
//        List<DoctorDTO> doctorsDTO = doctorService.findAll().stream()
//                .map(doctorMapper::map)
//                .toList();
//        log.info("Doctors: {}", doctorsDTO);
//        Map<SpecialtyDTO, List<DoctorDTO>> specialtyToDoctors = doctorsDTO.stream()
//                .collect(Collectors.groupingBy(DoctorDTO::getSpecialty));
//        log.info("Specialty to doctors: {}", specialtyToDoctors);
//
//        model.addAttribute("specialtiesDTO", specialtiesDTO);
//        model.addAttribute("specialtyToDoctors", specialtyToDoctors);
//
//        Appointment appointment = appointmentMapper.map(appointmentDTO);
//        log.info("Appointment: {}", appointment);
//        //appointmentService.save(appointment);
//        return "redirect:/patient/patient_appointment";
//
//    }

    private Patient getPatient(Principal principal) {
        ClinicUser clinicUser = userService.findByUserName(principal.getName());
        Patient patient = patientService.getPatientInfo(clinicUser.getId());
        return patient;
    }


}