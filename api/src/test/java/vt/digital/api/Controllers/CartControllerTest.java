package vt.digital.api.Controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import vt.digital.api.Models.CartItem;
import vt.digital.api.Service.CartService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void testAddItemToCart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(1);

        when(cartService.addItemToCart(1, 1)).thenReturn(cartItem);

        mockMvc.perform(post("/api/cart/add")
                .param("userId", "1")
                .param("productId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItemId").value(1));
    }

    @Test
    void testGetCartItems() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(1);

        when(cartService.getCartItems(1)).thenReturn(Arrays.asList(cartItem));

        mockMvc.perform(get("/api/cart/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cartItemId").value(1));
    }
}