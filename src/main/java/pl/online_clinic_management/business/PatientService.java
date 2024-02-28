package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.online_clinic_management.business.dao.PatientDAO;
import pl.online_clinic_management.domain.Patient;
import pl.online_clinic_management.domain.exception.NotFoundException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientDAO patientDAO;

    @Transactional
    public Patient getPatientInfo(Long patientId) {
        Optional<Patient> patient = patientDAO.findById(patientId);

        if (patient.isEmpty()) {
            throw new NotFoundException("Could not find patient by id: [%s]".formatted(patientId));
        }
        return patient.get();
    }


/*    private final ClinicUserRepository clinicUserRepository;
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public PatientInfoDAO getPatientInfo(Long patientId) {
        ClinicUser clinicUser = clinicUserRepository.findByPatientId(patientId);
        Address address = addressRepository.findByPatientId(patientId);
        Patient patient = patientRepository.findById(patientId);

        ClinicUserDTO clinicUserDTO = // map from clinicUser
                AddressDTO addressDTO = // map from address
                PatientDTO patientDTO = // map from patient

                PatientInfoDAO patientInfo = new PatientInfoDAO();
        patientInfo.setClinicUser(clinicUserDTO);
        patientInfo.setAddress(addressDTO);
        patientInfo.setPatient(patientDTO);

        return patientInfo;
    }*/

/*    @Transactional
    public Patient findPatient(String email) {
        Optional<Patient> customer = patientDAO.findByPesel(email);
        if (customer.isEmpty()) {
            throw new NotFoundException("Could not find patient by email: [%s]".formatted(email));
        }
        return customer.get();
    }*/

}
