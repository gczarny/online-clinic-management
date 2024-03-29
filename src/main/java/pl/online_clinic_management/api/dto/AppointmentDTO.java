package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;

import java.time.*;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    Long appointmentId;
    LocalDateTime appointmentDate;
    String status;
    String reason;
    PatientDTO patient;
    DoctorDTO doctor;
}
