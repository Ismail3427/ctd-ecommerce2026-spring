package com.ctdecomerce.store.retailers.controller;

import com.ctdecomerce.store.cart.repo.CartRepo;
import com.ctdecomerce.store.delivery.service.DeliveryService;
import com.ctdecomerce.store.discounts.repository.DiscountsRepo;
import com.ctdecomerce.store.orders.repository.OrdersRepo;
import com.ctdecomerce.store.product.repository.ProductRepo;
import com.ctdecomerce.store.retailers.service.RetailersService;
import com.ctdecomerce.store.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RetailersControllerTest {

    @Test
    void checkoutWebhookAliasRouteShouldBeReachable() throws Exception {
        RetailersController controller = new RetailersController(
                Mockito.mock(RetailersService.class),
                Mockito.mock(CartRepo.class),
                Mockito.mock(UserRepo.class),
                Mockito.mock(OrdersRepo.class),
                Mockito.mock(DeliveryService.class),
                Mockito.mock(DiscountsRepo.class),
                Mockito.mock(ProductRepo.class)
        );

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        String payload = "{\"id\":\"evt_test\",\"object\":\"event\",\"type\":\"checkout.session.completed\",\"data\":{\"object\":{\"id\":\"cs_test\",\"object\":\"checkout.session\",\"payment_intent\":\"pi_test\",\"metadata\":{\"userId\":\"1\"}}}}";

        mockMvc.perform(post("/webhook/checkout")
                        .content(payload)
                        .header("Stripe-Signature", "test-signature"))
                .andExpect(status().isBadRequest());
    }
}
