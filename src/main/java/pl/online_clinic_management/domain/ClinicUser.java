package pl.online_clinic_management.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"id", "userName", "email", "phone", "address"})
public class ClinicUser {

    Long id;
    String userName;
    String email;
    String phone;
    Boolean active;
    Address address;
    Set<Role> role;
}
