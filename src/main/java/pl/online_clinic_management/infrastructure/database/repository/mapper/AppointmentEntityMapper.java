package pl.online_clinic_management.infrastructure.database.repository.mapper;

import lombok.extern.slf4j.Slf4j;
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

    AppointmentEntity mapToEntity(Appointment appointment);

//    default AppointmentEntity mapToEntity(Appointment appointment) {
//        AppointmentEntity build = AppointmentEntity.builder()
//                //.appointmentId(appointment.getAppointmentId())
//                .appointmentDate(appointment.getAppointmentDate())
//                .status(appointment.getStatus())
//                .reason(appointment.getReason())
//                .patient(mapPatientToPatientEntity(appointment.getPatient()))
//                .doctor(mapDoctorToDoctorEntity(appointment.getDoctor()))
//                .build();
//        return build;
//    }

    private DoctorEntity mapDoctorToDoctorEntity(Doctor doctor) {
        if (Objects.nonNull(doctor)) {
            return DoctorEntity.builder()
                    .doctorId(doctor.getDoctorId())
                    .firstName(doctor.getFirstName())
                    .lastName(doctor.getLastName())
                    .build();
        }
        return null;
    }

    private PatientEntity mapPatientToPatientEntity(Patient patient) {
        if (Objects.nonNull(patient)) {
            return PatientEntity.builder()
                    .patientId(patient.getPatientId())
                    .firstName(patient.getFirstName())
                    .lastName(patient.getLastName())
                    .pesel(patient.getPesel())
                    .build();
        }
        return null;
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
