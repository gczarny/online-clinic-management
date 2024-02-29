package pl.online_clinic_management.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"appointmentId", "reason"})
@ToString(of = {"appointmentId", "appointmentDate", "status", "reason"})
public class Appointment {

    Long appointmentId;
    LocalDateTime appointmentDate;
    String status;
    String reason;
    Patient patient;
    Doctor doctor;
}
