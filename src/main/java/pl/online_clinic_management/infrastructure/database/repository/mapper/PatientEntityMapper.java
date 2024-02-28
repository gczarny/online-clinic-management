package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.online_clinic_management.domain.Address;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.domain.Role;
import pl.online_clinic_management.infrastructure.database.entity.AddressEntity;
import pl.online_clinic_management.infrastructure.database.entity.PatientEntity;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;
import pl.online_clinic_management.infrastructure.security.RoleEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper {


    default Patient mapFromObjectArray(List<Object[]> queryResults){
        var patientEntity = (PatientEntity) queryResults.get(0)[0];
        var clinicUserEntity = (ClinicUserEntity) queryResults.get(0)[1];
        var addressEntity = (AddressEntity) queryResults.get(0)[2];
        Set<Role> roles = queryResults.stream()
                .map(result -> (RoleEntity) result[3])
                .map(this::mapRoleEntityToRole)
                .collect(Collectors.toSet());

        Address address = mapAddressEntityToAddress(addressEntity);

        ClinicUser clinicUser = ClinicUser.builder()
                .id(clinicUserEntity.getId())
                .userName(clinicUserEntity.getUserName())
                .email(clinicUserEntity.getEmail())
                .phone(clinicUserEntity.getPhone())
                .active(clinicUserEntity.getActive())
                .address(address)
                .role(roles)
                .build();

        Patient patient = Patient.builder()
                .patientId(patientEntity.getPatientId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .pesel(patientEntity.getPesel())
                .clinicUser(clinicUser)
                .build();
        return patient;
    }

    private Address mapAddressEntityToAddress(AddressEntity addressEntity) {
        return Address.builder()
                .addressId(addressEntity.getAddressId())
                .country(addressEntity.getCountry())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .street(addressEntity.getStreet())
                .build();
    }

    private Role mapRoleEntityToRole(RoleEntity roleEntity) {
        return Role.builder()
                .roleId(roleEntity.getId())
                .roleName(roleEntity.getRoleName())
                .build();
    }
}
