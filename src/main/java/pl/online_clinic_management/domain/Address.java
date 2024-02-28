package pl.online_clinic_management.domain;


import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"addressId", "postalCode", "street"})
@ToString(of = {"addressId", "country", "city", "postalCode", "street"})
public class Address {

    Long addressId;
    String country;
    String city;
    String postalCode;
    String street;
}
