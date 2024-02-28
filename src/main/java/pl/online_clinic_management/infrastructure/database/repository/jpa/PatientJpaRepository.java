package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.DoctorEntity;

import java.util.List;

@Repository
public interface PatientJpaRepository extends JpaRepository<DoctorEntity, Long> {

    @Query(value = "SELECT p, cu, ad, roles " +
            "FROM PatientEntity p " +
            "INNER JOIN p.user cu " +
            "INNER JOIN cu.address ad " +
            "INNER JOIN cu.roles roles " +
            "WHERE p.id = :patientId")
    List<Object[]> getPatientInfo(@Param("patientId") Long patientId);

}
