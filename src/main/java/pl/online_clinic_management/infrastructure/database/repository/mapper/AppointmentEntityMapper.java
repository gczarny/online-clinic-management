package pl.online_clinic_management.infrastructure.database.repository.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.domain.Specialty;
import pl.online_clinic_management.infrastructure.database.entity.AppointmentEntity;
import pl.online_clinic_management.infrastructure.database.entity.DoctorEntity;
import pl.online_clinic_management.infrastructure.database.entity.PatientEntity;
import pl.online_clinic_management.infrastructure.database.entity.SpecialtyEntity;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mapstruct.ReportingPolicy.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AppointmentEntityMapper {

    //Appointment mapFromEntity(AppointmentEntity entity);
    default Appointment mapFromEntity(AppointmentEntity entity) {
        return Appointment.builder()
                .appointmentId(entity.getAppointmentId())
                .appointmentDate(entity.getAppointmentDate())
                .status(entity.getStatus())
                .reason(entity.getReason())
                .patient(mapPatientEntityToPatient(entity.getPatient()))
                .doctor(mapDoctorEntityToDoctor(entity.getDoctor()))
                .build();
    }

    private Patient mapPatientEntityToPatient(PatientEntity patientEntity) {
        if (Objects.nonNull(patientEntity)) {
            return Patient.builder()
                    .patientId(patientEntity.getPatientId())
                    .firstName(patientEntity.getFirstName())
                    .lastName(patientEntity.getLastName())
                    .pesel(patientEntity.getPesel())
                    .build();
        }
        return null;
    }

    private Doctor mapDoctorEntityToDoctor(DoctorEntity doctorEntity) {
        if (Objects.nonNull(doctorEntity)) {
            return Doctor.builder()
                    .doctorId(doctorEntity.getDoctorId())
                    .firstName(doctorEntity.getFirstName())
                    .lastName(doctorEntity.getLastName())
                    .specialties(mapSpecialtyEntityToSpecialty(doctorEntity.getSpecialties()))
                    .build();
        }
        return null;
    }

    Set<Specialty> mapSpecialtyEntityToSpecialty(Set<SpecialtyEntity> specialties);
}
