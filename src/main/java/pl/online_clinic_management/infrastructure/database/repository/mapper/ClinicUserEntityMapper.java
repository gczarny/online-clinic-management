package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

import static org.mapstruct.ReportingPolicy.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ClinicUserEntityMapper {

    //@Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    ClinicUser mapFromEntity(ClinicUserEntity entity);

}
