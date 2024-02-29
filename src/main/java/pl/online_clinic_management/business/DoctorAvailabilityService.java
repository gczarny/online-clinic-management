package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.online_clinic_management.business.dao.DoctorAvailabilityDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.DayOff;
import pl.online_clinic_management.domain.DoctorAvailability;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final AppointmentService appointmentService;

    @Transactional
    public List<DoctorAvailability> findByDoctorId(Long doctorId) {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityDAO.findByDoctorId(doctorId);
        log.info("Found for doctor availabilities: [{}]", doctorAvailabilities.size());
        return doctorAvailabilities;
    }
    @Transactional
    public List<DoctorAvailability> findByDoctorIdAndDateRangeStartBetween(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityDAO.findByDoctorIdAndDateRangeStartBetween(doctorId, dateFrom, dateTo);
        log.info("Found for doctor availabilities: [{}], between: [{}] - [{}]", doctorAvailabilities.size(), dateFrom, dateTo);
        return doctorAvailabilities;
    }

    @Transactional
    public List<DoctorAvailability> findAvailableTimesForDoctor(Long doctorId) {
        List<DoctorAvailability> availabilities = doctorAvailabilityDAO.findByDoctorId(doctorId);
        List<DayOff> dayOffs = dayOffService.findByDoctorId(doctorId);

        List<DoctorAvailability> filteredAvailabilities = new ArrayList<>();
        for (DoctorAvailability availability : availabilities) {
            filteredAvailabilities.addAll(splitAvailabilityExcludingDayOffs(availability, dayOffs));
        }

        return filteredAvailabilities;
    }

    @Transactional
    public List<String> getAvailableTimeSlotsForDoctor(Long doctorId, LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        log.info("Getting available time slots for doctor id:{} at date:{}", doctorId, dateTime);
        List<DoctorAvailability> availabilities = findAvailableTimesForDoctor(doctorId);
        log.info("Found availabilities: {}, for doctor id:{}", availabilities, doctorId);
        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
        log.info("Found appointments: {}, for doctor id:{} at date:{}", appointments, doctorId, dateTime);
        return getAvailableTimeSlots(availabilities, appointments, date);
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

    private List<String> getAvailableTimeSlots(List<DoctorAvailability> availabilities, List<Appointment> appointments, LocalDate date) {
        List<String> availableTimeSlots = new ArrayList<>();

        for (DoctorAvailability availability : availabilities) {
            log.info("Checking date: {} is between availability start date: {} and end date: {}", date, availability.getDateRangeStart(), availability.getDateRangeEnd());
            if ((availability.getDateRangeStart().isEqual(date) || availability.getDateRangeStart().isBefore(date))
                    && (availability.getDateRangeEnd().isEqual(date) || availability.getDateRangeEnd().isAfter(date))
            ) {
                log.info("Date is in range");
                LocalTime from = availability.getAvailableFrom();
                LocalTime until = availability.getAvailableUntil();

                while (from.isBefore(until)) {
                    String potentialSlot = from.toString();
                    LocalTime finalFrom = from;
                    boolean slotOccupied = appointments.stream()
                            .anyMatch(appointment -> (finalFrom.equals(appointment.getAppointmentDate().toLocalTime())
                                    || finalFrom.plusMinutes(30).isAfter(appointment.getAppointmentDate().toLocalTime()))
                                    && date.isEqual(appointment.getAppointmentDate().toLocalDate())); // add date checking here
                    if (!slotOccupied) {
                        availableTimeSlots.add(potentialSlot);
                    }
                    from = from.plusMinutes(30);  // or whatever interval you want
                }
            } else {
                log.info("Date is not in range");
            }
        }
        return availableTimeSlots;
    }
}
