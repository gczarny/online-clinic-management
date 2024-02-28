package pl.online_clinic_management.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"doctor_availability_id", "available_from", "available_until", "date_range_start", "date_range_end", "status", "doctor"})
@ToString(of = {"doctor_availability_id", "available_from", "available_until", "date_range_start", "date_range_end", "status", "doctor"})
public class DoctorAvailability {

    Long doctor_availability_id;
    Time available_from;
    Time available_until;
    Date date_range_start;
    Date date_range_end;
    String status;
    Doctor doctor;
}
