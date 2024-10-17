package vt.digital.api.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Models.Product;
import vt.digital.api.Models.User;
import vt.digital.api.Repositories.CartItemRepository;
import vt.digital.api.Repositories.ProductRepository;
import vt.digital.api.Repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddItemToCart() {
        User user = new User();
        user.setUserId(1);

        Product product = new Product();
        product.setProductId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        CartItem savedCartItem = new CartItem();
        savedCartItem.setUser(user);
        savedCartItem.setProduct(product);

        when(cartItemRepository.save(any(CartItem.class))).thenReturn(savedCartItem);

        CartItem result = cartService.addItemToCart(1, 1);
        assertEquals(user, result.getUser());
        assertEquals(product, result.getProduct());

        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void testGetCartItems() {
        User user = new User();
        user.setUserId(1);

        CartItem cartItem1 = new CartItem();
        cartItem1.setUser(user);

        CartItem cartItem2 = new CartItem();
        cartItem2.setUser(user);

        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);
        when(cartItemRepository.findByUserUserId(1)).thenReturn(cartItems);

        List<CartItem> result = cartService.getCartItems(1);
        assertEquals(2, result.size());

        verify(cartItemRepository, times(1)).findByUserUserId(1);
    }

    @Test
    void testRemoveItemFromCart() {
        cartService.removeItemFromCart(1);
        verify(cartItemRepository, times(1)).deleteById(1);
    }
}
