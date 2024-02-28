package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.SpecialtyDAO;
import pl.online_clinic_management.domain.Specialty;
import pl.online_clinic_management.infrastructure.database.repository.jpa.SpecialtyJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.SpecialtyEntityMapper;

import java.util.List;

@AllArgsConstructor
@Repository
public class SpecialtyRepository implements SpecialtyDAO {

    private final SpecialtyJpaRepository specialtyJpaRepository;
    private final SpecialtyEntityMapper mapper;

    @Override
    public List<Specialty> findAll() {
        return specialtyJpaRepository.findAll().stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
