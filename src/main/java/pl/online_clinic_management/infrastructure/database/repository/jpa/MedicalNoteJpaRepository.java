package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.MedicalNoteEntity;

@Repository
public interface MedicalNoteJpaRepository extends JpaRepository<MedicalNoteEntity, Long> {
}