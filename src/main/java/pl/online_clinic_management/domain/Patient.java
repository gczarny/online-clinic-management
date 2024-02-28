package pl.online_clinic_management.domain;

import lombok.*;
import pl.online_clinic_management.business.dao.AddressDAO;

@With
@Value
@Builder
@EqualsAndHashCode(of = "pesel")
@ToString(of = {"patientId", "firstName", "lastName", "pesel", "clinicUser"})
public class Patient {
    Long patientId;
    String firstName;
    String lastName;
    String pesel;
    ClinicUser clinicUser;
}
