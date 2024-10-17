package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"OrderTracking\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderTrackingID")
    private Integer orderTrackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order order;

    @Column(name = "TrackingNumber", nullable = false, length = 256)
    private String trackingNumber;
}
