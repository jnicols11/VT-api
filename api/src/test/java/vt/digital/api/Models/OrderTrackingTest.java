package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTrackingTest {

    private OrderTracking orderTracking;
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId(1001);

        orderTracking = OrderTracking.builder()
                .orderTrackingId(3003)
                .order(order)
                .trackingNumber("TRACK123456")
                .build();
    }

    @Test
    void testOrderTrackingCreation() {
        assertNotNull(orderTracking);
        assertEquals(3003, orderTracking.getOrderTrackingId());
        assertNotNull(orderTracking.getOrder());
        assertEquals(1001, orderTracking.getOrder().getOrderId());
        assertEquals("TRACK123456", orderTracking.getTrackingNumber());
    }

    @Test
    void testSettersAndGetters() {
        orderTracking.setOrderTrackingId(4004);
        assertEquals(4004, orderTracking.getOrderTrackingId());

        Order newOrder = new Order();
        newOrder.setOrderId(5005);
        orderTracking.setOrder(newOrder);
        assertEquals(5005, orderTracking.getOrder().getOrderId());

        orderTracking.setTrackingNumber("NEWTRACK789");
        assertEquals("NEWTRACK789", orderTracking.getTrackingNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderTracking sameOrderTracking = OrderTracking.builder()
                .orderTrackingId(3003)
                .order(order)
                .trackingNumber("TRACK123456")
                .build();

        assertEquals(orderTracking, sameOrderTracking);
        assertEquals(orderTracking.hashCode(), sameOrderTracking.hashCode());
    }
}