package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.domain.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    @Transactional
    public List<Appointment> findByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentDAO.findByPatientId(patientId);
        log.info("Found for patient appointments: [{}]", appointments.size());
        return appointments;
    }

    @Transactional
    public List<Appointment> findByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentDAO.findByDoctorId(doctorId);

        return appointments;
    }

    public List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        List<Appointment> appointments = appointmentDAO.findByDoctorIdAndDate(doctorId, date);

        return appointments;
    }

    @Transactional
    public Appointment save(Appointment appointment) {
        log.info("Saving appointment: [{}]", appointment);
        return appointmentDAO.save(appointment);
    }
}
