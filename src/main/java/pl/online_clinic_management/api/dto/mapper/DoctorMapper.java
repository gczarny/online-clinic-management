package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.ClinicUserDTO;
import pl.online_clinic_management.api.dto.DoctorDTO;
import pl.online_clinic_management.api.dto.SpecialtyDTO;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Specialty;
import pl.online_clinic_management.infrastructure.database.entity.SpecialtyEntity;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    default DoctorDTO map(final Doctor doctor) {
        return DoctorDTO.builder()
                .doctorId(doctor.getDoctorId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialties(mapToDTO(doctor.getSpecialties()))
                .clinicUser(mapToDTO(doctor.getClinicUser()))
                .build();
    }

    Set<SpecialtyDTO> mapToDTO(Set<Specialty> specialties);
    default ClinicUserDTO mapToDTO(ClinicUser user) {
        return ClinicUserDTO.builder()
                .clinicUserId(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
