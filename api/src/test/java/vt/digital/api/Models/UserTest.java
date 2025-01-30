package vt.digital.api.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class UserTest {

    @Test
    void testUserCreation() {
        User user = User.builder()
                .userId(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .build();

        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
    }

    @Test
    void testUserSetters() {
        User user = new User();

        user.setUserId(2);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.com");
        user.setPhone("0987654321");

        assertEquals(2, user.getUserId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("0987654321", user.getPhone());
    }

    @Test
    void testUserWithBillingAddresses() {
        User user = new User();
        user.setUserId(3);
        user.setFirstName("Alice");
        user.setLastName("Smith");

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setBillingAddressId(1);
        billingAddress.setStreet("123 Main St");
        billingAddress.setCity("New York");
        billingAddress.setState("NY");
        billingAddress.setCountry("USA");
        billingAddress.setZipCode("10001");
        billingAddress.setUser(user);

        Set<BillingAddress> billingAddresses = new HashSet<>();
        billingAddresses.add(billingAddress);
        user.setBillingAddresses(billingAddresses);

        assertNotNull(user.getBillingAddresses());
        assertEquals(1, user.getBillingAddresses().size());
        assertEquals(billingAddress, user.getBillingAddresses().iterator().next());
    }

    @Test
    void testUserWithCartItems() {
        User user = new User();
        user.setUserId(4);

        Product product = new Product();
        product.setProductId(1);
        product.setName("Laptop");

        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(1);
        cartItem.setUser(user);
        cartItem.setProduct(product);

        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);
        user.setCartItems(cartItems);

        assertNotNull(user.getCartItems());
        assertEquals(1, user.getCartItems().size());
        assertEquals(cartItem, user.getCartItems().iterator().next());
    }

    @Test
    void testUserWithOrders() {
        User user = new User();
        user.setUserId(5);

        Order order = new Order();
        order.setOrderId(1);
        order.setUser(user);
        order.setStatus("Pending");

        Set<Order> orders = new HashSet<>();
        orders.add(order);
        user.setOrders(orders);

        assertNotNull(user.getOrders());
        assertEquals(1, user.getOrders().size());
        assertEquals(order, user.getOrders().iterator().next());
    }
}