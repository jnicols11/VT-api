package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vt.digital.api.Models.GuestOrder;
import vt.digital.api.Models.Order;
import vt.digital.api.Models.OrderItem;
import vt.digital.api.Models.Product;
import vt.digital.api.Repositories.GuestOrderRepository;
import vt.digital.api.Repositories.OrderItemRepository;
import vt.digital.api.Repositories.OrderRepository;
import vt.digital.api.Repositories.ProductRepository;

@Service
public class GuestOrderService {

    @Autowired
    private GuestOrderRepository guestOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public GuestOrder placeGuestOrder(String email, Integer productId, String street, String city, String state, String country, String zipCode) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setStreet(street);
        order.setCity(city);
        order.setState(state);
        order.setCountry(country);
        order.setZipCode(zipCode);
        order.setStatus("Placed");

        Order savedOrder = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(savedOrder);
        orderItem.setProduct(product);
        orderItemRepository.save(orderItem);

        GuestOrder guestOrder = new GuestOrder();
        guestOrder.setEmail(email);
        guestOrder.setOrder(savedOrder);

        return guestOrderRepository.save(guestOrder);
    }
}
