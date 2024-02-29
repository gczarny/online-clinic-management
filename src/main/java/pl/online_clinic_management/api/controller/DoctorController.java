package pl.online_clinic_management.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.online_clinic_management.business.DoctorAvailabilityService;
import pl.online_clinic_management.domain.DoctorAvailability;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(DoctorController.DOCTOR)
public class DoctorController {

//    public static final String DOCTOR = "/doctor";
//    private final DoctorAvailabilityService doctorAvailabilityService;
//
//    @GetMapping("/{doctorId}/availabilities")
//    public List<String> getAvailabilities(@PathVariable Long doctorId,
//                                          @RequestParam("datetime")
//                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {
//
//        return doctorAvailabilityService.getAvailableTimeSlotsForDoctor(doctorId, datetime);
//    }

}
