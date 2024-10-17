package vt.digital.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vt.digital.api.Models.Order;
import vt.digital.api.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestParam Integer userId,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String country,
            @RequestParam String zipCode) {
        
        Order order = orderService.placeOrder(userId, street, city, state, country, zipCode);
        return ResponseEntity.ok(order);
    }
}
