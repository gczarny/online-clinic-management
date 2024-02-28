package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.online_clinic_management.business.dao.DoctorDAO;
import pl.online_clinic_management.domain.Doctor;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class DoctorService {

    private final DoctorDAO doctorDAO;

    @Transactional
    public Doctor getDoctorInfo(Long doctorId) {
        Optional<Doctor> doctor = doctorDAO.findById(doctorId);

        if (doctor.isEmpty()) {
            throw new NotFoundException("Could not find doctor by id: [%s]".formatted(doctorId));
        }
        return doctor.get();
    }

    public List<Doctor> findAll() {
        List<Doctor> doctors = doctorDAO.findAll();
        log.info("Available parts: [{}]", doctors);
        return doctors;
    }
}
