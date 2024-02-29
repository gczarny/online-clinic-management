package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "doctorAvailabilityId")
@ToString(of = {"doctorAvailabilityId", "availableFrom",
        "dateRangeStart", "dateRangeEnd","status"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_availability")
public class DoctorAvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_availability_id")
    private Long doctorAvailabilityId;

    @Column(name = "available_from")
    private LocalTime availableFrom;

    @Column(name = "available_until")
    private LocalTime availableUntil;

    @Column(name = "date_range_start")
    private LocalDate dateRangeStart;

    @Column(name = "date_range_end")
    private LocalDate dateRangeEnd;

    @Column(name = "status")
    @Length(min = 1, max = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
}
