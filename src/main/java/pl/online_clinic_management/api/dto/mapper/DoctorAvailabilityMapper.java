package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.DoctorAvailabilityDTO;
import pl.online_clinic_management.domain.DoctorAvailability;

@Mapper(componentModel = "spring")
public interface DoctorAvailabilityMapper {

    DoctorAvailabilityDTO map(final DoctorAvailability doctorAvailability);

}
