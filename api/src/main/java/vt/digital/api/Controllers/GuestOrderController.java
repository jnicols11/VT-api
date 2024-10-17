package vt.digital.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vt.digital.api.Models.GuestOrder;
import vt.digital.api.Service.GuestOrderService;

@RestController
@RequestMapping("/api/guest-orders")
public class GuestOrderController {

    @Autowired
    private GuestOrderService guestOrderService;

    @PostMapping("/place")
    public ResponseEntity<GuestOrder> placeGuestOrder(
            @RequestParam String email,
            @RequestParam Integer productId,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String country,
            @RequestParam String zipCode) {
        
        GuestOrder guestOrder = guestOrderService.placeGuestOrder(email, productId, street, city, state, country, zipCode);
        return ResponseEntity.ok(guestOrder);
    }
}
