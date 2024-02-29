package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByPatient_PatientId(Long patientId);
    Optional<List<AppointmentEntity>> findByDoctor_DoctorId(Long doctorId);

    Optional<AppointmentEntity> findByDoctor_DoctorIdAndAppointmentDate(Long doctorId, LocalDateTime date);
}