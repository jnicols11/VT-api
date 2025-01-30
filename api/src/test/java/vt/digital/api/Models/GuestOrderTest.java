package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestOrderTest {

    private GuestOrder guestOrder;
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId(1);

        guestOrder = GuestOrder.builder()
                .guestOrderId(1001)
                .order(order)
                .email("guest@example.com")
                .build();
    }

    @Test
    void testGuestOrderCreation() {
        assertNotNull(guestOrder);
        assertEquals(1001, guestOrder.getGuestOrderId());
        assertEquals("guest@example.com", guestOrder.getEmail());
        assertNotNull(guestOrder.getOrder());
        assertEquals(1, guestOrder.getOrder().getOrderId());
    }

    @Test
    void testSettersAndGetters() {
        guestOrder.setGuestOrderId(2002);
        assertEquals(2002, guestOrder.getGuestOrderId());

        guestOrder.setEmail("newguest@example.com");
        assertEquals("newguest@example.com", guestOrder.getEmail());

        Order newOrder = new Order();
        newOrder.setOrderId(2);
        guestOrder.setOrder(newOrder);
        assertEquals(2, guestOrder.getOrder().getOrderId());
    }

    @Test
    void testEqualsAndHashCode() {
        GuestOrder sameGuestOrder = GuestOrder.builder()
                .guestOrderId(1001)
                .order(order)
                .email("guest@example.com")
                .build();

        assertEquals(guestOrder, sameGuestOrder);
        assertEquals(guestOrder.hashCode(), sameGuestOrder.hashCode());
    }

    @Test
    void testGuestOrderBuilder() {
        Order anotherOrder = new Order();
        anotherOrder.setOrderId(3);

        GuestOrder newGuestOrder = GuestOrder.builder()
                .guestOrderId(3003)
                .order(anotherOrder)
                .email("anotherguest@example.com")
                .build();

        assertNotNull(newGuestOrder);
        assertEquals(3003, newGuestOrder.getGuestOrderId());
        assertEquals("anotherguest@example.com", newGuestOrder.getEmail());
        assertNotNull(newGuestOrder.getOrder());
        assertEquals(3, newGuestOrder.getOrder().getOrderId());
    }
}