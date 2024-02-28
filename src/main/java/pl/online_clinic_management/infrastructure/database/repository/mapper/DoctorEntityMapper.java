package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.online_clinic_management.domain.*;
import pl.online_clinic_management.infrastructure.database.entity.AddressEntity;
import pl.online_clinic_management.infrastructure.database.entity.DoctorEntity;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;
import pl.online_clinic_management.infrastructure.security.RoleEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

    Doctor mapFromEntity(DoctorEntity entity);

    default Doctor mapFromObjectArray(List<Object[]> queryResults){
        var doctorEntity = (DoctorEntity) queryResults.get(0)[0];
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

        Doctor doctor = Doctor.builder()
                .doctorId(doctorEntity.getDoctorId())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .clinicUser(clinicUser)
                .build();
        return doctor;
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
