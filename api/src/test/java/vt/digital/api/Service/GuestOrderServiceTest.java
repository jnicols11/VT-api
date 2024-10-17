package vt.digital.api.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vt.digital.api.Models.GuestOrder;
import vt.digital.api.Models.Order;
import vt.digital.api.Models.Product;
import vt.digital.api.Repositories.GuestOrderRepository;
import vt.digital.api.Repositories.OrderItemRepository;
import vt.digital.api.Repositories.OrderRepository;
import vt.digital.api.Repositories.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class GuestOrderServiceTest {

    @Mock
    private GuestOrderRepository guestOrderRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GuestOrderService guestOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceGuestOrder() {
        Product product = new Product();
        product.setProductId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Order order = new Order();
        order.setOrderId(1);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        GuestOrder guestOrder = new GuestOrder();
        guestOrder.setGuestOrderId(1);
        guestOrder.setOrder(order);

        when(guestOrderRepository.save(any(GuestOrder.class))).thenReturn(guestOrder);

        GuestOrder result = guestOrderService.placeGuestOrder("guest@example.com", 1, "Street", "City", "State", "Country", "ZipCode");

        assertEquals(1, result.getGuestOrderId());
        verify(guestOrderRepository, times(1)).save(any(GuestOrder.class));
    }
}