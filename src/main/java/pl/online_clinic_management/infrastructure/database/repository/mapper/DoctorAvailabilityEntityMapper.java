package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.online_clinic_management.domain.DoctorAvailability;
import pl.online_clinic_management.infrastructure.database.entity.DoctorAvailabilityEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorAvailabilityEntityMapper {

    DoctorAvailability mapFromEntity(DoctorAvailabilityEntity doctorAvailabilityEntity);
}
