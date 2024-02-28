package pl.online_clinic_management.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.infrastructure.database.entity.AddressEntity;

import java.util.Optional;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findById(Long id);
}
