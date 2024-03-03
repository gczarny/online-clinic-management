package pl.online_clinic_management.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.online_clinic_management.api.dto.AppointmentDTO;
import pl.online_clinic_management.api.dto.DoctorDTO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentDTO map(final Appointment appointment);
    default Appointment map(final AppointmentDTO appointmentDTO){
        return Appointment.builder()
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .status(appointmentDTO.getStatus())
                .reason(appointmentDTO.getReason())
                .patient(Patient.builder()
                        .patientId(appointmentDTO.getPatient().getPatientId())
                        .firstName(appointmentDTO.getPatient().getFirstName())
                        .lastName(appointmentDTO.getPatient().getLastName())
                        .pesel(appointmentDTO.getPatient().getPesel())
                        .build())
                .doctor(Doctor.builder()
                        .doctorId(appointmentDTO.getDoctor().getDoctorId())
                        .firstName(appointmentDTO.getDoctor().getFirstName())
                        .lastName(appointmentDTO.getDoctor().getLastName())
                        .build())
                .build();
    }


}
