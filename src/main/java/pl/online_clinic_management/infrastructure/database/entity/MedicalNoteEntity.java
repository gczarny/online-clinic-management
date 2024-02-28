package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(of = "medicalNoteId")
@ToString(of = {"medicalNoteId", "content", "appointment"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medical_note")
public class MedicalNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_note_id")
    private Long medicalNoteId;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;
}
