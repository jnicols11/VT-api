package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductColorTest {

    private ProductColor productColor;
    private Product product;
    private Color color;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .productId(1)
                .name("Test Product")
                .description("Test Description")
                .price(null)
                .quantity(10)
                .build();

        color = Color.builder()
                .colorId(1)
                .name("Red")
                .build();

        productColor = ProductColor.builder()
                .productColorId(1)
                .product(product)
                .color(color)
                .build();
    }

    @Test
    void testProductColorCreation() {
        assertNotNull(productColor);
        assertEquals(1, productColor.getProductColorId());
        assertEquals(product, productColor.getProduct());
        assertEquals(color, productColor.getColor());
    }

    @Test
    void testSettersAndGetters() {
        Product newProduct = Product.builder()
                .productId(2)
                .name("Updated Product")
                .build();
        
        Color newColor = Color.builder()
                .colorId(2)
                .name("Blue")
                .build();

        productColor.setProductColorId(2);
        assertEquals(2, productColor.getProductColorId());

        productColor.setProduct(newProduct);
        assertEquals(newProduct, productColor.getProduct());

        productColor.setColor(newColor);
        assertEquals(newColor, productColor.getColor());
    }

    @Test
    void testEqualsAndHashCode() {
        ProductColor sameProductColor = ProductColor.builder()
                .productColorId(1)
                .product(product)
                .color(color)
                .build();

        assertEquals(productColor, sameProductColor);
        assertEquals(productColor.hashCode(), sameProductColor.hashCode());
    }
}