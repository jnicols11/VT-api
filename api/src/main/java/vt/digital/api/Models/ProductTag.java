package vt.digital.api.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"ProductTag\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductTagID")
    private Integer productTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TagID", nullable = false)
    private Tag tag;
}
