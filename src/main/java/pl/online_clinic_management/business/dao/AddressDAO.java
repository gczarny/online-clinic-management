package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Address;
import java.util.Optional;

public interface AddressDAO {

    Optional<Address> findByUserId(Long userId);
}