package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentDAO {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    Appointment save(Appointment appointment);
}
