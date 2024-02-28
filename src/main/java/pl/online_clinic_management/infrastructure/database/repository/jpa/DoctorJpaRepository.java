package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.DoctorEntity;

import java.util.List;

@Repository
public interface DoctorJpaRepository extends JpaRepository<DoctorEntity, Long> {

    @Query(value = "SELECT d, cu, ad, roles " +
            "FROM DoctorEntity d " +
            "INNER JOIN d.user cu " +
            "INNER JOIN cu.address ad " +
            "INNER JOIN cu.roles roles " +
            "WHERE d.id = :doctorId")
    List<Object[]> getDoctorInfo(@Param("doctorId") Long doctorId);

}
