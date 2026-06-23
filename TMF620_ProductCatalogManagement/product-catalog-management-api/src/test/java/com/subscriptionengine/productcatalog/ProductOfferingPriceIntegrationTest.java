package com.subscriptionengine.productcatalog;

import org.junit.jupiter.api.Test;
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
public class ProductOfferingPriceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProductOfferingPrice() throws Exception {
        String payload = """
                {
                  "name": "Standard Monthly Price",
                  "description": "Monthly recurring charge for the plan",
                  "isBundle": false,
                  "priceType": "recurring",
                  "recurringChargePeriodType": "month",
                  "recurringChargePeriodLength": 1,
                  "price": {
                    "unit": "USD",
                    "value": 49.99
                  },
                  "unitOfMeasure": {
                    "amount": 1.0,
                    "units": "month"
                  },
                  "lifecycleStatus": "Active"
                }
                """;

        mockMvc.perform(post("/tmf-api/productCatalogManagement/v4/productOfferingPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Standard Monthly Price"))
                .andExpect(jsonPath("$.priceType").value("recurring"))
                .andExpect(jsonPath("$.price.unit").value("USD"))
                .andExpect(jsonPath("$.price.value").value(49.99));
    }
}

