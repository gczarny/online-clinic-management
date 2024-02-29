package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "appointmentId")
@ToString(of = {"appointmentId", "appointmentDate", "status", "reason"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "appointment_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime appointmentDate;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
    private Set<MedicalNoteEntity> medicalNotes;
}
