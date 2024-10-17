package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Models.Order;
import vt.digital.api.Models.OrderItem;
import vt.digital.api.Repositories.CartItemRepository;
import vt.digital.api.Repositories.OrderItemRepository;
import vt.digital.api.Repositories.OrderRepository;
import vt.digital.api.Repositories.UserRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Integer userId, String street, String city, String state, String country, String zipCode) {
        List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);

        Order order = new Order();
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        order.setStreet(street);
        order.setCity(city);
        order.setState(state);
        order.setCountry(country);
        order.setZipCode(zipCode);
        order.setStatus("Placed");

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }
}
