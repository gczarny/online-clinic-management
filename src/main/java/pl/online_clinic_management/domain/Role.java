package pl.online_clinic_management.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "roleName")
@ToString(of = {"roleId", "roleName"})
public class Role {

    Long roleId;
    String roleName;
}
