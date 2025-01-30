package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;
    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);

        product = new Product();
        product.setProductId(100);
        product.setName("Sample Product");

        cartItem = CartItem.builder()
                .cartItemId(1)
                .user(user)
                .product(product)
                .build();
    }

    @Test
    void testCartItemCreation() {
        assertNotNull(cartItem);
        assertEquals(1, cartItem.getCartItemId());
        assertNotNull(cartItem.getUser());
        assertEquals(1, cartItem.getUser().getUserId());
        assertNotNull(cartItem.getProduct());
        assertEquals(100, cartItem.getProduct().getProductId());
        assertEquals("Sample Product", cartItem.getProduct().getName());
    }

    @Test
    void testSettersAndGetters() {
        User newUser = new User();
        newUser.setUserId(2);

        Product newProduct = new Product();
        newProduct.setProductId(200);
        newProduct.setName("New Product");

        cartItem.setUser(newUser);
        assertEquals(2, cartItem.getUser().getUserId());

        cartItem.setProduct(newProduct);
        assertEquals(200, cartItem.getProduct().getProductId());
        assertEquals("New Product", cartItem.getProduct().getName());
    }

    @Test
    void testEqualsAndHashCode() {
        CartItem sameCartItem = CartItem.builder()
                .cartItemId(1)
                .user(user)
                .product(product)
                .build();

        assertEquals(cartItem, sameCartItem);
        assertEquals(cartItem.hashCode(), sameCartItem.hashCode());
    }

    @Test
    void testCartItemBuilder() {
        User anotherUser = new User();
        anotherUser.setUserId(3);

        Product anotherProduct = new Product();
        anotherProduct.setProductId(300);
        anotherProduct.setName("Another Product");

        CartItem newCartItem = CartItem.builder()
                .cartItemId(2)
                .user(anotherUser)
                .product(anotherProduct)
                .build();

        assertNotNull(newCartItem);
        assertEquals(2, newCartItem.getCartItemId());
        assertNotNull(newCartItem.getUser());
        assertEquals(3, newCartItem.getUser().getUserId());
        assertNotNull(newCartItem.getProduct());
        assertEquals(300, newCartItem.getProduct().getProductId());
        assertEquals("Another Product", newCartItem.getProduct().getName());
    }
}