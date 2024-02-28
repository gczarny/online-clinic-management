package pl.online_clinic_management.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"appointmentId", "reason"})
@ToString(of = {"appointmentId", "appointmentDate", "status", "reason"})
public class Appointment {

    Long appointmentId;
    OffsetDateTime appointmentDate;
    String status;
    String reason;
    Patient patient;
    Doctor doctor;
}
