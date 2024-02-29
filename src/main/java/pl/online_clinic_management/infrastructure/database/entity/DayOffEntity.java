package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"dayOffId", "dateOff"})
@ToString(of = {"dayOffId", "doctor", "dateOff"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "day_off")
public class DayOffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_off_id")
    private Long dayOffId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @Column(name = "date_off", nullable = false)
    private LocalDate dateOff;

}
