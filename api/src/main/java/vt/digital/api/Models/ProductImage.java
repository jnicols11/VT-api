package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"ProductImage\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductImageID")
    private Integer productImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Lob
    @Column(name = "Image", nullable = false)
    private byte[] image;
}
