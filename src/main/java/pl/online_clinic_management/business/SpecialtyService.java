package pl.online_clinic_management.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.online_clinic_management.business.dao.SpecialtyDAO;
import pl.online_clinic_management.domain.Specialty;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SpecialtyService {

    private final SpecialtyDAO specialtyDAO;

    public List<Specialty> findAll() {
        List<Specialty> specialties = specialtyDAO.findAll();
        log.info("Found specialties: [{}]", specialties.size());
        return specialties;
    }
}
