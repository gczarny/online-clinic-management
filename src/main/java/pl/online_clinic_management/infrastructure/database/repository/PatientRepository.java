package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.PatientDAO;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.PatientEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PatientRepository implements PatientDAO {

    private final PatientJpaRepository patientJpaRepository;
    private final PatientEntityMapper patientEntityMapper;

    @Override
    public Optional<Patient> findByPesel(String pesel) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        List<Object[]> patientInfo = patientJpaRepository.getPatientInfo(id);
        return Optional.of(patientEntityMapper.mapFromObjectArray(patientInfo));
    }
}
