package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Models.Product;
import vt.digital.api.Models.User;
import vt.digital.api.Repositories.CartItemRepository;
import vt.digital.api.Repositories.ProductRepository;
import vt.digital.api.Repositories.UserRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public CartItem addItemToCart(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Integer userId) {
        return cartItemRepository.findByUserUserId(userId);
    }

    public void removeItemFromCart(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCartForUser(Integer userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);
        cartItemRepository.deleteAll(cartItems);
    }
}
