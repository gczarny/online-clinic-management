package pl.online_clinic_management.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"doctorId", "firstName", "lastName"})
@ToString(of = {"doctorId", "firstName", "lastName", "specialties", "clinicUser"})
public class Doctor {
    Long doctorId;
    String firstName;
    String lastName;
    Set<Specialty> specialties;
    ClinicUser clinicUser;
}
