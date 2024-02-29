package pl.online_clinic_management.domain;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"dayOffId", "dateOff"})
@ToString(of = {"dayOffId", "doctor", "dateOff"})
public class DayOff {

    Long dayOffId;
    Doctor doctor;
    LocalDate dateOff;
}
