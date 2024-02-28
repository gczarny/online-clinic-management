package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient_history")
public class PatientHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_history_id")
    private Long patientHistoryId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
}
