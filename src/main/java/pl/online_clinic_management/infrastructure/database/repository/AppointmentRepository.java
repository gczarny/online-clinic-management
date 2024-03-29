package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.infrastructure.database.entity.AppointmentEntity;
import pl.online_clinic_management.infrastructure.database.repository.jpa.AppointmentJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.AppointmentEntityMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Repository
public class AppointmentRepository implements AppointmentDAO {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentEntityMapper mapper;

    @Override
    public List<Appointment> findByPatientId(Long patientId) {

        return appointmentJpaRepository.findByPatient_PatientId(patientId).stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        Optional<List<AppointmentEntity>> appointments = appointmentJpaRepository.findByDoctor_DoctorId(doctorId);
        return appointments
                .orElse(Collections.emptyList())
                .stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        return appointmentJpaRepository.findByDoctor_DoctorIdAndAppointmentDate(doctorId, date)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment save(Appointment appointment) {
        log.info("Saving appointment in repository: [{}]", appointment);
        AppointmentEntity appointmentEntity = appointmentJpaRepository.saveAndFlush(mapper.mapToEntity(appointment));
        return mapper.mapFromEntity(appointmentEntity);
    }

}
