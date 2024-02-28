package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.ClinicUser;
import java.util.Optional;

public interface ClinicUserDAO {

    Optional<ClinicUser> findByPatientId(Long patientId);
}