package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.online_clinic_management.business.dao.DayOffDAO;
import pl.online_clinic_management.domain.DayOff;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DayOffService {

    private final DayOffDAO dayOffDAO;

    public List<DayOff> findByDoctorId(Long doctorId) {
        List<DayOff> dayOffs = dayOffDAO.findByDoctorId(doctorId);
        log.info("Found for doctor day offs: [{}]", dayOffs.size());
        return dayOffs;
    }

    public List<DayOff> findByDoctorIdAndDateOffBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        List<DayOff> dayOffs = dayOffDAO.findByDoctorIdAndDateOffBetween(doctorId, dateFrom, dateTo);
        log.info("Found for doctor day offs: [{}], between: [{}] - [{}]", dayOffs.size(), dateFrom, dateTo);
        return dayOffs;
    }

}
