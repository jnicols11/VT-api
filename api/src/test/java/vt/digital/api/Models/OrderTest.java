package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);

        order = Order.builder()
                .orderId(1001)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .status("Processing")
                .zipCode("62704")
                .user(user)
                .guestOrders(new HashSet<>())
                .orderItems(new HashSet<>())
                .orderTrackings(new HashSet<>())
                .build();
    }

    @Test
    void testOrderCreation() {
        assertNotNull(order);
        assertEquals(1001, order.getOrderId());
        assertEquals("123 Main St", order.getStreet());
        assertEquals("Springfield", order.getCity());
        assertEquals("IL", order.getState());
        assertEquals("USA", order.getCountry());
        assertEquals("Processing", order.getStatus());
        assertEquals("62704", order.getZipCode());
        assertNotNull(order.getUser());
        assertEquals(1, order.getUser().getUserId());
        assertTrue(order.getGuestOrders().isEmpty());
        assertTrue(order.getOrderItems().isEmpty());
        assertTrue(order.getOrderTrackings().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        order.setOrderId(2002);
        assertEquals(2002, order.getOrderId());

        order.setStreet("456 Elm St");
        assertEquals("456 Elm St", order.getStreet());

        order.setCity("Los Angeles");
        assertEquals("Los Angeles", order.getCity());

        order.setState("CA");
        assertEquals("CA", order.getState());

        order.setCountry("Canada");
        assertEquals("Canada", order.getCountry());

        order.setStatus("Shipped");
        assertEquals("Shipped", order.getStatus());

        order.setZipCode("90001");
        assertEquals("90001", order.getZipCode());

        User newUser = new User();
        newUser.setUserId(2);
        order.setUser(newUser);
        assertEquals(2, order.getUser().getUserId());
    }

    @Test
    void testOrderAssociations() {
        GuestOrder guestOrder = GuestOrder.builder().guestOrderId(1).order(order).email("guest@example.com").build();
        OrderItem orderItem = OrderItem.builder().order(order).build();
        OrderTracking orderTracking = OrderTracking.builder().order(order).build();

        Set<GuestOrder> guestOrders = new HashSet<>();
        guestOrders.add(guestOrder);
        order.setGuestOrders(guestOrders);

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);

        Set<OrderTracking> orderTrackings = new HashSet<>();
        orderTrackings.add(orderTracking);
        order.setOrderTrackings(orderTrackings);

        assertFalse(order.getGuestOrders().isEmpty());
        assertFalse(order.getOrderItems().isEmpty());
        assertFalse(order.getOrderTrackings().isEmpty());

        assertEquals(1, order.getGuestOrders().size());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(1, order.getOrderTrackings().size());
    }

    @Test
    void testEqualsAndHashCode() {
        Order sameOrder = Order.builder()
                .orderId(1001)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .status("Processing")
                .zipCode("62704")
                .user(user)
                .guestOrders(new HashSet<>())
                .orderItems(new HashSet<>())
                .orderTrackings(new HashSet<>())
                .build();

        assertEquals(order, sameOrder);
        assertEquals(order.hashCode(), sameOrder.hashCode());
    }
}