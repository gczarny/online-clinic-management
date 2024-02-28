package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Patient;

import java.util.Optional;

public interface PatientDAO {

    Optional<Patient> findByPesel(String pesel);
    Optional<Patient> findById(Long id);

}
