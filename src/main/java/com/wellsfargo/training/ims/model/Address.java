package com.wellsfargo.training.ims.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AddressId;
    private @NonNull String street;
    private @NonNull String city;
    private int pincode;

    @OneToOne
    @JoinColumn(name ="dealer_id")
    private Dealer dealer;

    public Address(String street, String city, int pincode) {
        this.street = street;
        this.city = city;
        this.pincode = pincode;
    }
}
