package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.online_clinic_management.business.dao.DoctorAvailabilityDAO;
import pl.online_clinic_management.domain.DayOff;
import pl.online_clinic_management.domain.DoctorAvailability;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorAvailabilityService {

    private final DoctorAvailabilityDAO doctorAvailabilityDAO;
    private final DayOffService dayOffService;

    public List<DoctorAvailability> findByDoctorId(Long doctorId) {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityDAO.findByDoctorId(doctorId);
        log.info("Found for doctor availabilities: [{}]", doctorAvailabilities.size());
        return doctorAvailabilities;
    }

    public List<DoctorAvailability> findByDoctorIdAndDateRangeStartBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityDAO.findByDoctorIdAndDateRangeStartBetween(doctorId, dateFrom, dateTo);
        log.info("Found for doctor availabilities: [{}], between: [{}] - [{}]", doctorAvailabilities.size(), dateFrom, dateTo);
        return doctorAvailabilities;
    }

    public List<DoctorAvailability> findAvailableTimesForDoctor(Long doctorId) {
        List<DoctorAvailability> availabilities = doctorAvailabilityDAO.findByDoctorId(doctorId);
        List<DayOff> dayOffs = dayOffService.findByDoctorId(doctorId);

        List<DoctorAvailability> filteredAvailabilities = new ArrayList<>();
        for (DoctorAvailability availability : availabilities) {
            filteredAvailabilities.addAll(splitAvailabilityExcludingDayOffs(availability, dayOffs));
        }

        return filteredAvailabilities;
    }

    private List<DoctorAvailability> splitAvailabilityExcludingDayOffs(DoctorAvailability availability, List<DayOff> dayOffs) {
        Set<LocalDate> offDays = dayOffs.stream().map(DayOff::getDateOff).collect(Collectors.toSet());
        LocalDate current = availability.getDateRangeStart();
        LocalDate end = availability.getDateRangeEnd();
        List<DoctorAvailability> splitAvailabilities = new ArrayList<>();

        while (current.isBefore(end)) {
            LocalDate until = current;

            while (until.isBefore(end) && !offDays.contains(until)) {
                until = until.plusDays(1);
            }

            splitAvailabilities.add(DoctorAvailability.builder()
                            .doctorAvailabilityId(availability.getDoctorAvailabilityId())
                            .availableFrom(availability.getAvailableFrom())
                            .availableUntil(availability.getAvailableUntil())
                            .dateRangeStart(current)
                            .dateRangeEnd(until.minusDays(1))// subtract 1 because 'until' is the start of an off day or end of the range
                            .status(availability.getStatus())
                            .doctor(availability.getDoctor())
                    .build());

            current = until.plusDays(1); // skip the off day
        }

        return splitAvailabilities;
    }
}
