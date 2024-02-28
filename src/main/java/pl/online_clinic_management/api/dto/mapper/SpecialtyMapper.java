package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.SpecialtyDTO;
import pl.online_clinic_management.domain.Specialty;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    SpecialtyDTO map(final Specialty specialty);

}
