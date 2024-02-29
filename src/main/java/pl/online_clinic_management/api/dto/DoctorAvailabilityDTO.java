package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailabilityDTO {

    Long doctorAvailabilityId;
    LocalTime availableFrom;
    LocalTime availableUntil;
    LocalDate dateRangeStart;
    LocalDate dateRangeEnd;
    String status;
    DoctorDTO doctor;
}
