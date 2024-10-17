package vt.digital.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        CartItem cartItem = cartService.addItemToCart(userId, productId);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Integer userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Integer cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Integer userId) {
        cartService.clearCartForUser(userId);
        return ResponseEntity.noContent().build();
    }
}