package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "doctorId")
@ToString(of = {"doctorId", "firstName", "lastName", "specialization"})
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

    @Column(name = "specialization")
    private String specialization;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ClinicUserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<DoctorAvailabilityEntity> doctorAvailabilities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<AppointmentEntity> appointments;

}
