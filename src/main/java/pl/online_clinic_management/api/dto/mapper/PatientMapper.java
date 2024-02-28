package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.PatientDTO;
import pl.online_clinic_management.domain.Patient;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO map(final Patient patient);
}
