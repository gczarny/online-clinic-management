package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.online_clinic_management.domain.Doctor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffDTO {

    Long dayOffId;
    Doctor doctor;
    LocalDate dateOff;
}
