package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private CartItem cartItem;
    private OrderItem orderItem;
    private ProductColor productColor;
    private ProductImage productImage;
    private ProductTag productTag;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .productId(1)
                .name("Test Product")
                .description("This is a test product.")
                .price(BigDecimal.valueOf(99.99))
                .quantity(10)
                .cartItems(new HashSet<>())
                .orderItems(new HashSet<>())
                .productColors(new HashSet<>())
                .productImages(new HashSet<>())
                .productTags(new HashSet<>())
                .build();

        cartItem = CartItem.builder().cartItemId(101).product(product).build();
        orderItem = OrderItem.builder().orderItemId(202).product(product).build();
        productColor = ProductColor.builder().product(product).build();
        productImage = ProductImage.builder().product(product).build();
        productTag = ProductTag.builder().product(product).build();
    }

    @Test
    void testProductCreation() {
        assertNotNull(product);
        assertEquals(1, product.getProductId());
        assertEquals("Test Product", product.getName());
        assertEquals("This is a test product.", product.getDescription());
        assertEquals(BigDecimal.valueOf(99.99), product.getPrice());
        assertEquals(10, product.getQuantity());
        assertTrue(product.getCartItems().isEmpty());
        assertTrue(product.getOrderItems().isEmpty());
        assertTrue(product.getProductColors().isEmpty());
        assertTrue(product.getProductImages().isEmpty());
        assertTrue(product.getProductTags().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        product.setProductId(2);
        assertEquals(2, product.getProductId());

        product.setName("Updated Product");
        assertEquals("Updated Product", product.getName());

        product.setDescription("Updated description.");
        assertEquals("Updated description.", product.getDescription());

        product.setPrice(BigDecimal.valueOf(59.99));
        assertEquals(BigDecimal.valueOf(59.99), product.getPrice());

        product.setQuantity(20);
        assertEquals(20, product.getQuantity());
    }

    @Test
    void testProductAssociations() {
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);
        product.setCartItems(cartItems);
        assertFalse(product.getCartItems().isEmpty());
        assertEquals(1, product.getCartItems().size());

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        product.setOrderItems(orderItems);
        assertFalse(product.getOrderItems().isEmpty());
        assertEquals(1, product.getOrderItems().size());

        Set<ProductColor> productColors = new HashSet<>();
        productColors.add(productColor);
        product.setProductColors(productColors);
        assertFalse(product.getProductColors().isEmpty());
        assertEquals(1, product.getProductColors().size());

        Set<ProductImage> productImages = new HashSet<>();
        productImages.add(productImage);
        product.setProductImages(productImages);
        assertFalse(product.getProductImages().isEmpty());
        assertEquals(1, product.getProductImages().size());

        Set<ProductTag> productTags = new HashSet<>();
        productTags.add(productTag);
        product.setProductTags(productTags);
        assertFalse(product.getProductTags().isEmpty());
        assertEquals(1, product.getProductTags().size());
    }

    @Test
    void testEqualsAndHashCode() {
        Product sameProduct = Product.builder()
                .productId(1)
                .name("Test Product")
                .description("This is a test product.")
                .price(BigDecimal.valueOf(99.99))
                .quantity(10)
                .build();

        assertEquals(product, sameProduct);
        assertEquals(product.hashCode(), sameProduct.hashCode());
    }
}