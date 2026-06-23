package com.subscriptionengine.payment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.model.Money;
import org.openapitools.model.PaymentCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PaymentApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePayment() throws Exception {
        PaymentCreate paymentCreate = new PaymentCreate();
        paymentCreate.setDescription("Test Payment");
        
        Money amount = new Money();
        amount.setValue(100.5f);
        amount.setUnit("USD");
        paymentCreate.setAmount(amount);
        paymentCreate.setTotalAmount(amount);

        org.openapitools.model.AccountRef account = new org.openapitools.model.AccountRef();
        account.setId("acc-123");
        paymentCreate.setAccount(account);

        org.openapitools.model.PaymentMethodRefOrValue paymentMethod = new org.openapitools.model.PaymentMethodRefOrValue();
        paymentMethod.setId("pm-123");
        paymentCreate.setPaymentMethod(paymentMethod);

        mockMvc.perform(post("/tmf-api/paymentManagement/v4/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("created"))
                .andExpect(jsonPath("$.amount.value").value(100.5))
                .andExpect(jsonPath("$.amount.unit").value("USD"));
    }
}
