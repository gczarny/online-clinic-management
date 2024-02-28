package pl.online_clinic_management.infrastructure.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.ClinicUserDAO;

import java.util.Optional;

@Repository
public interface ClinicUserRepository extends JpaRepository<ClinicUserEntity, Long>{//}, ClinicUserDAO {
    ClinicUserEntity findByUserName(String userName);

//    Optional<ClinicUserEntity> findByDoctor_Id(Long doctorId);
//    Optional<ClinicUserEntity> findByPatient_Id(Long patientId);
}
