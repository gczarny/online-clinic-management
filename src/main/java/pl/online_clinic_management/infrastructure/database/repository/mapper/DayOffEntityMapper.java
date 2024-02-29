package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.online_clinic_management.domain.DayOff;
import pl.online_clinic_management.infrastructure.database.entity.DayOffEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DayOffEntityMapper {

    DayOff mapFromEntity(DayOffEntity dayOffEntity);
}
