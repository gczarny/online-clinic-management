package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.online_clinic_management.domain.Specialty;
import pl.online_clinic_management.infrastructure.database.entity.SpecialtyEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialtyEntityMapper {

    Specialty mapFromEntity(SpecialtyEntity entity);
}
