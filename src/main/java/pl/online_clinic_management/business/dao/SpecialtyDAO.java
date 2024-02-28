package pl.online_clinic_management.business.dao;

import pl.online_clinic_management.domain.Specialty;

import java.util.List;

public interface SpecialtyDAO {

    List<Specialty> findAll();

}
