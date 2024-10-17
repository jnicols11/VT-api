package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"ProductColor\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductColorID")
    private Integer productColorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorID", nullable = false)
    private Color color;
}
