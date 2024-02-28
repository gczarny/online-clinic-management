package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Role;

import java.util.Set;

public interface RoleDAO {

    Set<Role> findByUserId(Long userId);
}
