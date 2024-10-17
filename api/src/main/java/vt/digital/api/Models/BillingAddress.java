package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"BillingAddress\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillingAddressID")
    private Integer billingAddressId;

    @Column(name = "Street", nullable = false, length = 256)
    private String street;

    @Column(name = "City", nullable = false, length = 256)
    private String city;

    @Column(name = "State", nullable = false, length = 5)
    private String state;

    @Column(name = "Country", nullable = false, length = 256)
    private String country;

    @Column(name = "ZipCode", nullable = false, length = 35)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;
}
