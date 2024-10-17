package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "\"Order\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderId;

    @Column(name = "Street", nullable = false, length = 500)
    private String street;

    @Column(name = "City", nullable = false, length = 250)
    private String city;

    @Column(name = "State", nullable = false, length = 4)
    private String state;

    @Column(name = "Country", nullable = false, length = 200)
    private String country;

    @Column(name = "Status", nullable = false, length = 25)
    private String status;

    @Column(name = "ZipCode", nullable = false, length = 35)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GuestOrder> guestOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderTracking> orderTrackings;
}