package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.DoctorDAO;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.DoctorEntityMapper;
import pl.online_clinic_management.infrastructure.database.repository.mapper.PatientEntityMapper;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class DoctorRepository implements DoctorDAO {

    private final DoctorJpaRepository doctorJpaRepository;
    private final DoctorEntityMapper doctorEntityMapper;
    @Override
    public Optional<Doctor> findById(Long id) {
        List<Object[]> patientInfo = doctorJpaRepository.getDoctorInfo(id);
        return Optional.of(doctorEntityMapper.mapFromObjectArray(patientInfo));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorJpaRepository.findAll().stream()
            .map(doctorEntityMapper::mapFromEntity)
            .toList();
    }
}
