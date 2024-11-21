package vt.digital.api.Models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"Color\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color {

    public Color(Integer colorId2) {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ColorID")
    private Integer colorId;

    @Column(name = "Name", nullable = false, length = 36)
    private String name;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductColor> productColors;
}
