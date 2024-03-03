package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailabilityDTO {

    Long doctorAvailabilityId;
    LocalTime availableFrom;
    LocalTime availableUntil;
    LocalDate dateRangeStart;
    LocalDate dateRangeEnd;
    String status;
    DoctorDTO doctor;
    List<LocalDateTime> halfHourTimeSlots;

    public List<String> getHalfHourTimeSlots() {
        List<String> slots = new ArrayList<>();
        LocalTime time = this.availableFrom;
        while (!time.isAfter(this.availableUntil.minusMinutes(29))) {
            LocalTime end = time.plusMinutes(30);
            slots.add(time + " - " + end.toString());
            time = end;
        }
        return slots;
    }

    public void generateAvailableTimeSlots(List<AppointmentDTO> appointments) {
        this.halfHourTimeSlots = new ArrayList<>();
        // Zakładając, że appointments są już przefiltrowane dla danego lekarza i daty
        for (LocalDate date = dateRangeStart; !date.isAfter(dateRangeEnd); date = date.plusDays(1)) {
            LocalTime time = availableFrom;
            while (time.isBefore(availableUntil)) {
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                if (appointments.stream().noneMatch(a -> a.getAppointmentDate().isEqual(dateTime))) {
                    halfHourTimeSlots.add(dateTime);
                }
                time = time.plusMinutes(30); // Przejście do następnego slotu czasowego
            }
        }
    }
}
