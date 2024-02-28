package pl.online_clinic_management.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.online_clinic_management.api.dto.PatientDTO;
import pl.online_clinic_management.api.dto.mapper.PatientMapper;
import pl.online_clinic_management.business.PatientService;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.infrastructure.security.OnlineClinicManagementUserDetailsService;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PatientController {

    private static final String PATIENT = "/patient";

    private final PatientService patientService;
    private final OnlineClinicManagementUserDetailsService userService;
    private final PatientMapper patientMapper;

    @GetMapping(value = PATIENT)
    public String homePage(Principal principal, Model model) {

        ClinicUser clinicUser = userService.findByUserName(principal.getName());
        Patient patient = patientService.getPatientInfo(clinicUser.getId());
        PatientDTO patientDTO = patientMapper.map(patient);

        model.addAttribute("patientDTO", patientDTO);
        return "patient_portal";
    }


}