package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    private OrderItem orderItem;
    private Order order;
    private Product product;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId(1001);

        product = new Product();
        product.setProductId(2002);

        orderItem = OrderItem.builder()
                .orderItemId(3003)
                .order(order)
                .product(product)
                .build();
    }

    @Test
    void testOrderItemCreation() {
        assertNotNull(orderItem);
        assertEquals(3003, orderItem.getOrderItemId());
        assertNotNull(orderItem.getOrder());
        assertEquals(1001, orderItem.getOrder().getOrderId());
        assertNotNull(orderItem.getProduct());
        assertEquals(2002, orderItem.getProduct().getProductId());
    }

    @Test
    void testSettersAndGetters() {
        orderItem.setOrderItemId(4004);
        assertEquals(4004, orderItem.getOrderItemId());

        Order newOrder = new Order();
        newOrder.setOrderId(5005);
        orderItem.setOrder(newOrder);
        assertEquals(5005, orderItem.getOrder().getOrderId());

        Product newProduct = new Product();
        newProduct.setProductId(6006);
        orderItem.setProduct(newProduct);
        assertEquals(6006, orderItem.getProduct().getProductId());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderItem sameOrderItem = OrderItem.builder()
                .orderItemId(3003)
                .order(order)
                .product(product)
                .build();

        assertEquals(orderItem, sameOrderItem);
        assertEquals(orderItem.hashCode(), sameOrderItem.hashCode());
    }
}