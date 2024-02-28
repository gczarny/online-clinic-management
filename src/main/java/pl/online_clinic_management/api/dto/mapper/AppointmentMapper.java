package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.AppointmentDTO;
import pl.online_clinic_management.domain.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentDTO map(final Appointment appointment);
    Appointment map(final AppointmentDTO appointmentDTO);
}
