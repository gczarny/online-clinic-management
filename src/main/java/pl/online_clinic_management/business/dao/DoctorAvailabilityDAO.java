package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.DoctorAvailability;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DoctorAvailabilityDAO {

    List<DoctorAvailability> findByDoctorId(Long doctorId);
    List<DoctorAvailability> findByDoctorIdAndDateRangeStartBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo);
}
