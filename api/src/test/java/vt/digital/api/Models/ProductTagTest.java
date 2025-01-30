package vt.digital.api.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductImageTest {

    @Test
    void testProductImageCreation() {
        Product product = Product.builder()
                .productId(1)
                .name("Test Product")
                .description("Test Description")
                .price(new java.math.BigDecimal("99.99"))
                .quantity(10)
                .build();

        byte[] sampleImage = new byte[]{1, 2, 3, 4, 5};

        ProductImage productImage = ProductImage.builder()
                .productImageId(1)
                .product(product)
                .image(sampleImage)
                .build();

        assertNotNull(productImage);
        assertEquals(1, productImage.getProductImageId());
        assertEquals(product, productImage.getProduct());
        assertArrayEquals(sampleImage, productImage.getImage());
    }

    @Test
    void testProductImageSetters() {
        ProductImage productImage = new ProductImage();

        Product product = new Product();
        product.setProductId(2);
        product.setName("Updated Product");

        byte[] updatedImage = new byte[]{10, 20, 30, 40, 50};

        productImage.setProductImageId(2);
        productImage.setProduct(product);
        productImage.setImage(updatedImage);

        assertEquals(2, productImage.getProductImageId());
        assertEquals(product, productImage.getProduct());
        assertArrayEquals(updatedImage, productImage.getImage());
    }
}