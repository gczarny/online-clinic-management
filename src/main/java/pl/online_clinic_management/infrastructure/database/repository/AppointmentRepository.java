package pl.online_clinic_management.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.online_clinic_management.business.dao.AppointmentDAO;
import pl.online_clinic_management.domain.Appointment;
import pl.online_clinic_management.infrastructure.database.entity.AppointmentEntity;
import pl.online_clinic_management.infrastructure.database.repository.jpa.AppointmentJpaRepository;
import pl.online_clinic_management.infrastructure.database.repository.mapper.AppointmentEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class AppointmentRepository implements AppointmentDAO {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentEntityMapper mapper;

    @Override
    public List<Appointment> findByPatientId(Long patientId) {

        Optional<AppointmentEntity> patient = appointmentJpaRepository.findByPatient_PatientId(patientId);
        return patient.stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentJpaRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(mapper::mapFromEntity)
                .collect(Collectors.toList());
    }

}
