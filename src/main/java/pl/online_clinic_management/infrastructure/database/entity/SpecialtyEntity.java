package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(of = {"specialtyId", "name"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialty")
public class SpecialtyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialty_id")
    private Long specialtyId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "specialties", fetch = FetchType.EAGER)
    private Set<DoctorEntity> doctors;
}
