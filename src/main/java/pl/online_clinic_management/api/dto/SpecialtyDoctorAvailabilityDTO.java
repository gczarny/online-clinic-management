package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDoctorAvailabilityDTO {
    private String specialty;
    private DoctorDTO doctor;
    private LocalDate date;
    private List<String> availableHours;
}
