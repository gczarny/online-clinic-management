package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorWithDatesDTO {
    private DoctorDTO doctor;
    private Map<LocalDate, List<String>> availableTimeSlots;
}
