package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.online_clinic_management.domain.Role;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicUserDTO {

    private Long clinicUserId;
    private String userName;
    private String email;
    private String phone;
    private Boolean active;
    private AddressDTO address;
    private Set<Role> role;
}
