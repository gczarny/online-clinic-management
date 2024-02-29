package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.DayOffEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayOffJpaRepository extends JpaRepository<DayOffEntity, Long> {
    List<DayOffEntity> findByDoctor_DoctorId(Long doctorId);

    List<DayOffEntity> findByDoctor_DoctorIdAndDateOffBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo);
}
