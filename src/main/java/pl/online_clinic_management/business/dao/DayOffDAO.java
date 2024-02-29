package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface DayOffDAO {

    List<DayOff> findByDoctorId(Long doctorId);

    List<DayOff> findByDoctorIdAndDateOffBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo);
}
