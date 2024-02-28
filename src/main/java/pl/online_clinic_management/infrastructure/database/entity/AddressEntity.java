package pl.online_clinic_management.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import pl.online_clinic_management.infrastructure.security.ClinicUserEntity;

@Getter
@Setter
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "street"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address")
    private ClinicUserEntity user;
}
