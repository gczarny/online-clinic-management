package pl.online_clinic_management.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"doctorAvailabilityId", "doctor"})
@ToString(of = {"doctorAvailabilityId", "availableFrom", "availableUntil", "dateRangeStart", "dateRangeEnd", "status", "doctor"})
public class DoctorAvailability {

    Long doctorAvailabilityId;
    LocalTime availableFrom;
    LocalTime availableUntil;
    LocalDate dateRangeStart;
    LocalDate dateRangeEnd;
    String status;
    Doctor doctor;

}
