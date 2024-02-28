package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.DoctorDTO;
import pl.online_clinic_management.domain.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO map(final Doctor doctor);
}
