package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.AddressDAO;
import pl.online_clinic_management.domain.Address;
import pl.online_clinic_management.infrastructure.database.repository.jpa.AddressJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    private final AddressJpaRepository addressJpaRepository;

    @Override
    public Optional<Address> findByUserId(Long userId) {
        return Optional.empty();
    }
}
