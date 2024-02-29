package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.DoctorAvailabilityEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorAvailabilityJpaRepository extends JpaRepository<DoctorAvailabilityEntity, Long> {

    List<DoctorAvailabilityEntity> findByDoctor_DoctorId(Long doctorId);

    List<DoctorAvailabilityEntity> findByDoctor_DoctorIdAndDateRangeStartBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo);
}