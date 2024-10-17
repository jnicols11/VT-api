package vt.digital.api.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Models.Order;
import vt.digital.api.Models.User;
import vt.digital.api.Repositories.CartItemRepository;
import vt.digital.api.Repositories.OrderItemRepository;
import vt.digital.api.Repositories.OrderRepository;
import vt.digital.api.Repositories.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        User user = new User();
        user.setUserId(1);

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(cartItemRepository.findByUserUserId(1)).thenReturn(Arrays.asList(cartItem));

        Order order = new Order();
        order.setOrderId(1);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.placeOrder(1, "Street", "City", "State", "Country", "ZipCode");

        assertEquals(1, result.getOrderId());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartItemRepository, times(1)).deleteAll(anyList());
    }
}
