package pl.online_clinic_management.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.sql.Time;

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
    private Time availableFrom;

    @Column(name = "available_until")
    private Time availableUntil;

    @Column(name = "date_range_start")
    private Date dateRangeStart;

    @Column(name = "date_range_end")
    private Date dateRangeEnd;

    @Column(name = "status")
    @Length(min = 1, max = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
}
