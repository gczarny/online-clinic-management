package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"doctorId", "firstName", "lastName"})
@ToString(of = {"doctorId", "firstName", "lastName", "specialties"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ClinicUserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_specialty",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<SpecialtyEntity> specialties;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<DoctorAvailabilityEntity> doctorAvailabilities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<AppointmentEntity> appointments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<DayOffEntity> dayOffs;

}
