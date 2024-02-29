package pl.online_clinic_management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    public List<String> getHalfHourTimeSlots() {
        List<String> slots = new ArrayList<>();
        LocalTime time = this.availableFrom;
        while (!time.isAfter(this.availableUntil.minusMinutes(29))) {
            LocalTime end = time.plusMinutes(30);
            slots.add(time.toString() + " - " + end.toString());
            time = end;
        }
        return slots;
    }
}
