package pl.online_clinic_management.api.dto;

import pl.online_clinic_management.domain.Doctor;

import java.util.Date;
import java.sql.Time;

public class DoctorAvailabilityDTO {

    Long doctor_availability_id;
    Time available_from;
    Time available_until;
    Date date_range_start;
    Date date_range_end;
    String status;
    Doctor doctor;
}
