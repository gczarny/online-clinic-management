package pl.online_clinic_management.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.online_clinic_management.infrastructure.database.entity.DoctorEntity;

@With
@Value
@Builder
@EqualsAndHashCode(of = "name")
@ToString(of = {"specialtyId", "name"})
public class Specialty {

    Long specialtyId;
    String name;
}
