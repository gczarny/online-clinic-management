package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.AppointmentEntity;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {
}