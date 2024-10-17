package vt.digital.api.Controllers;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import vt.digital.api.Models.GuestOrder;
import vt.digital.api.Service.GuestOrderService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

@WebMvcTest(GuestOrderController.class)
class GuestOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestOrderService guestOrderService;

    @Test
    void testPlaceGuestOrder() throws Exception {
        GuestOrder guestOrder = new GuestOrder();
        guestOrder.setGuestOrderId(1);

        when(guestOrderService.placeGuestOrder(any(String.class), any(Integer.class), any(String.class), any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(guestOrder);

        mockMvc.perform(post("/api/guest-orders/place")
                .param("email", "guest@example.com")
                .param("productId", "1")
                .param("street", "123 Main St")
                .param("city", "Somewhere")
                .param("state", "CA")
                .param("country", "USA")
                .param("zipCode", "12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestOrderId").value(1));
    }
}
