package pl.online_clinic_management.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.DoctorAvailabilityDAO;
import pl.online_clinic_management.domain.DoctorAvailability;
import pl.online_clinic_management.infrastructure.database.repository.jpa.DoctorAvailabilityJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.DoctorAvailabilityEntityMapper;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Repository
public class DoctorAvailabilityRepository implements DoctorAvailabilityDAO {

    private final DoctorAvailabilityJpaRepository doctorAvailabilityJpaRepository;
    private final DoctorAvailabilityEntityMapper mapper;
    @Override
    public List<DoctorAvailability> findByDoctorId(Long doctorId) {
        return doctorAvailabilityJpaRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(toList());
    }

    @Override
    public List<DoctorAvailability> findByDoctorIdAndDateRangeStartBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        return doctorAvailabilityJpaRepository.findByDoctor_DoctorIdAndDateRangeStartBetween(doctorId, dateFrom, dateTo)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(toList());
    }

}
