package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyWithDoctorsDTO {
    private String specialty;
    private List<DoctorWithDatesDTO> doctors;
}
