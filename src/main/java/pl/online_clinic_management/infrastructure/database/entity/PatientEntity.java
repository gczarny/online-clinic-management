package pl.online_clinic_management.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "patientId")
@ToString(of = {"patientId", "firstName", "lastName", "pesel"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pesel")
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ClinicUserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    private Set<AppointmentEntity> appointments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    private Set<PatientHistoryEntity> patientHistories;
}
