package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class ColorTest {

    private Color color;
    private ProductColor productColor;

    @BeforeEach
    void setUp() {
        productColor = new ProductColor();
        productColor.setProductColorId(1);

        Set<ProductColor> productColors = new HashSet<>();
        productColors.add(productColor);

        color = Color.builder()
                .colorId(1)
                .name("Red")
                .productColors(productColors)
                .build();
    }

    @Test
    void testColorCreation() {
        assertNotNull(color);
        assertEquals(1, color.getColorId());
        assertEquals("Red", color.getName());
        assertNotNull(color.getProductColors());
        assertEquals(1, color.getProductColors().size());
    }

    @Test
    void testSettersAndGetters() {
        color.setColorId(2);
        assertEquals(2, color.getColorId());

        color.setName("Blue");
        assertEquals("Blue", color.getName());

        Set<ProductColor> newProductColors = new HashSet<>();
        ProductColor anotherProductColor = new ProductColor();
        anotherProductColor.setProductColorId(2);
        newProductColors.add(anotherProductColor);

        color.setProductColors(newProductColors);
        assertEquals(1, color.getProductColors().size());
        assertEquals(2, color.getProductColors().iterator().next().getProductColorId());
    }

    @Test
    void testEqualsAndHashCode() {
        Color sameColor = Color.builder()
                .colorId(1)
                .name("Red")
                .productColors(color.getProductColors())
                .build();

        assertEquals(color, sameColor);
        assertEquals(color.hashCode(), sameColor.hashCode());
    }

    @Test
    void testColorBuilder() {
        Set<ProductColor> anotherProductColors = new HashSet<>();
        ProductColor anotherProductColor = new ProductColor();
        anotherProductColor.setProductColorId(3);
        anotherProductColors.add(anotherProductColor);

        Color newColor = Color.builder()
                .colorId(3)
                .name("Green")
                .productColors(anotherProductColors)
                .build();

        assertNotNull(newColor);
        assertEquals(3, newColor.getColorId());
        assertEquals("Green", newColor.getName());
        assertNotNull(newColor.getProductColors());
        assertEquals(1, newColor.getProductColors().size());
        assertEquals(3, newColor.getProductColors().iterator().next().getProductColorId());
    }
}