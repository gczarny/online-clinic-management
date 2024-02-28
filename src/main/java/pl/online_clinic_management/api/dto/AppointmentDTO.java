package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    Long appointmentId;
    OffsetDateTime appointmentDate;
    String status;
    String reason;
    PatientDTO patient;
    DoctorDTO doctor;
}
