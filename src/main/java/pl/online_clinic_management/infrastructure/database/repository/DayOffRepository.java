package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.DayOffDAO;
import pl.online_clinic_management.domain.DayOff;
import pl.online_clinic_management.infrastructure.database.repository.jpa.DayOffJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.DayOffEntityMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class DayOffRepository implements DayOffDAO {

    private final DayOffJpaRepository dayOffJpaRepository;
    private final DayOffEntityMapper mapper;

    @Override
    public List<DayOff> findByDoctorId(Long doctorId) {
        return dayOffJpaRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DayOff> findByDoctorIdAndDateOffBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        return dayOffJpaRepository.findByDoctor_DoctorIdAndDateOffBetween(doctorId, dateFrom, dateTo)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
