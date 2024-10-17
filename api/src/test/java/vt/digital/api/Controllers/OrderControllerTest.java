package vt.digital.api.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import vt.digital.api.Models.Order;
import vt.digital.api.Service.OrderService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testPlaceOrder() throws Exception {
        Order order = new Order();
        order.setOrderId(1);

        when(orderService.placeOrder(any(Integer.class), any(String.class), any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(order);

        mockMvc.perform(post("/api/orders/place")
                .param("userId", "1")
                .param("street", "123 Main St")
                .param("city", "Somewhere")
                .param("state", "CA")
                .param("country", "USA")
                .param("zipCode", "12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1));
    }
}