package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"GuestOrder\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuestOrderID")
    private Integer guestOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order order;

    @Column(name = "Email", nullable = false)
    private String email;
}
