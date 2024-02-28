package pl.online_clinic_management.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.online_clinic_management.api.dto.AppointmentDTO;
import pl.online_clinic_management.api.dto.PatientDTO;
import pl.online_clinic_management.api.dto.mapper.AppointmentMapper;
import pl.online_clinic_management.api.dto.mapper.PatientMapper;
import pl.online_clinic_management.business.AppointmentService;
import pl.online_clinic_management.business.PatientService;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.infrastructure.security.OnlineClinicManagementUserDetailsService;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PatientController {

    private static final String PATIENT = "/patient";
    private static final String PATIENT_INFO = "/patient_info";
    private static final String PATIENT_APPOINTMENT = "/patient/patient_appointment";
    private static final String PATIENT_APPOINTMENT_INFO = "/patient_appointment_notes";
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final OnlineClinicManagementUserDetailsService userService;

    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

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

        model.addAttribute("appointmentDTO", appointments);
        return "patient_appointment";
    }


    private Patient getPatient(Principal principal) {
        ClinicUser clinicUser = userService.findByUserName(principal.getName());
        Patient patient = patientService.getPatientInfo(clinicUser.getId());
        return patient;
    }


}