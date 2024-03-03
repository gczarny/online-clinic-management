package pl.online_clinic_management.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

/*    @ResponseBody
    @GetMapping("/patient/{doctorId}/available-slots")
    public List<String> getDoctorAvailabilities(
            @PathVariable Long doctorId,
            @RequestParam(name = "datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime
    ) {
        List<String> availableTimeSlotsForDoctor = doctorAvailabilityService.getAvailableTimeSlotsForDoctor(doctorId, dateTime);
        log.info("Available time slots for doctor: {}", availableTimeSlotsForDoctor);
        return availableTimeSlotsForDoctor;
    }*/

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
        doctorsDTO.forEach(doctorDTO ->
                doctorDTO.getSpecialties()
                        .forEach(specialtyDTO ->
                                specialtyToDoctors
                                        .computeIfAbsent(specialtyDTO.getName(), k -> new ArrayList<>())
                                        .add(doctorDTO)
                        )
        );
        log.info("Specialty to doctors: {}", specialtyToDoctors);

        Map<Long, List<DoctorAvailabilityDTO>> doctorToAvailabilities = new HashMap<>();
        Map<Long, List<String>> doctorToAvailableDates = new HashMap<>();

        doctorsDTO.forEach(doctorDTO -> {
            List<DoctorAvailability> availabilities = doctorAvailabilityService.findAvailableTimesForDoctor(doctorDTO.getDoctorId());
            List<DoctorAvailabilityDTO> availabilityDTOS = availabilities.stream()
                    .map(doctorAvailabilityMapper::map)
                    .collect(Collectors.toList());
            doctorToAvailabilities.put(doctorDTO.getDoctorId(), availabilityDTOS);

            List<String> availableDates = new ArrayList<>();
            availabilityDTOS.forEach(availabilitiesDTO -> {
                LocalDate start = availabilitiesDTO.getDateRangeStart();
                LocalDate end = availabilitiesDTO.getDateRangeEnd();
                List<LocalDate> dates = Stream.iterate(start, date -> date.isBefore(end), date -> date.plusDays(1))
                        .toList();
                availableDates.addAll(dates.stream().map(LocalDate::toString).toList());
            });
            doctorToAvailableDates.put(doctorDTO.getDoctorId(), availableDates);
        });

        Map<Long, DoctorDTO> doctorDTOMap = doctorsDTO.stream()
                .collect(Collectors.toMap(DoctorDTO::getDoctorId, doctorDTO -> doctorDTO));

        Map<DoctorDTO,  Map<LocalDate, List<String>>> doctorToAvailableTimeSlots = new HashMap<>();
        doctorsDTO.forEach(doctorDTO -> {
            Map<LocalDate, List<String>> availableTimeSlots = new HashMap<>();
            List<String> availableDates = doctorToAvailableDates.get(doctorDTO.getDoctorId());
            availableDates.forEach(date -> {
                LocalDate localDate = LocalDate.parse(date);
                Map<LocalDate, List<String>> availableTimeSlotsForDate = doctorAvailabilityService.getAvailableTimeSlotsForDoctor(doctorDTO.getDoctorId(), localDate, localDate);
                availableTimeSlots.putAll(availableTimeSlotsForDate);
            });
            doctorToAvailableTimeSlots.put(doctorDTO, availableTimeSlots);
        });
        log.info("Doctor to available time slots: {}", doctorToAvailableTimeSlots);

        //now make a map, key as map of specialties with doctor, and values as map of available time slots
        Map<Map<String, List<DoctorDTO>>, Map<LocalDate, List<String>>> specialtyToDoctorsWithAvailableTimeSlots = new HashMap<>();
        specialtyToDoctors.forEach((specialty, doctors) -> {
            Map<LocalDate, List<String>> availableTimeSlots = new HashMap<>();
            doctors.forEach(doctor -> {
                Map<LocalDate, List<String>> availableTimeSlotsForDoctor = doctorToAvailableTimeSlots.get(doctor);
                availableTimeSlots.putAll(availableTimeSlotsForDoctor);
            });
            specialtyToDoctorsWithAvailableTimeSlots.put(Map.of(specialty, doctors), availableTimeSlots);
        });
        log.info("Specialty to doctors with available time slots: {}", specialtyToDoctorsWithAvailableTimeSlots);

        List<SpecialtyDoctorAvailabilityDTO> availabilityDTOList = new ArrayList<>();
        specialtyToDoctorsWithAvailableTimeSlots.forEach((specialtyDoctorsMap, timeSlots) -> {
            specialtyDoctorsMap.forEach((specialty, doctors) -> {
                doctors.forEach(doctor -> {
                    timeSlots.forEach((date, hours) -> {
                        SpecialtyDoctorAvailabilityDTO dto = new SpecialtyDoctorAvailabilityDTO(specialty, doctor, date, hours);
                        availabilityDTOList.add(dto);
                    });
                });
            });
        });

        Map<String, SpecialtyWithDoctorsDTO> specialtyWithDoctors = new HashMap<>();

        specialtyToDoctors.forEach((specialty, doctors) -> {
            List<DoctorWithDatesDTO> doctorWithDatesDTOs = doctors.stream().map(doctor -> {
                Map<LocalDate, List<String>> availableTimeSlots = doctorToAvailableTimeSlots.get(doctor);
                return new DoctorWithDatesDTO(doctor, availableTimeSlots);
            }).collect(Collectors.toList());

            specialtyWithDoctors.put(specialty, new SpecialtyWithDoctorsDTO(specialty, doctorWithDatesDTOs));
        });
        log.info("Specialty with doctors: {}", specialtyWithDoctors);

        return Map.of(
                "appointmentsDTO", appointmentsDTO,
                "specialtiesDTO", specialtiesDTO,
                "specialtyWithUniqueDoctors", specialtyToDoctors,
                "appointmentDTO", AppointmentDTO.builder().appointmentDate(LocalDateTime.now()).build(),
                "doctorDTOMap", doctorDTOMap,
                "doctorToAvailableTimeSlots", doctorToAvailableTimeSlots,
                "availabilityDTOList", availabilityDTOList,
                "specialtyWithDoctors", specialtyWithDoctors
        );
    }

}