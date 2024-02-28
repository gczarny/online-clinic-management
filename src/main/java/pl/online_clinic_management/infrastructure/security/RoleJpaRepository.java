package pl.online_clinic_management.infrastructure.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleName(String role);

//    Set<RoleEntity> findByUser_Id(Long userId);
}
