package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.online_clinic_management.domain.ClinicUser;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long patientId;
    private String firstName;
    private String lastName;
    private String pesel;
    private ClinicUserDTO clinicUser;

}
