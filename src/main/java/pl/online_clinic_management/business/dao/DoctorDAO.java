package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorDAO {

    Optional<Doctor> findById(Long id);
    List<Doctor> findAll();
}
