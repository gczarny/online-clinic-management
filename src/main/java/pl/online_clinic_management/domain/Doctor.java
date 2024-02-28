package pl.online_clinic_management.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"doctorId", "firstName", "lastName"})
@ToString(of = {"doctorId", "firstName", "lastName", "specialization", "clinicUser"})
public class Doctor {
    Long doctorId;
    String firstName;
    String lastName;
    String specialization;
    ClinicUser clinicUser;
}
