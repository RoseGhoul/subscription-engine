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
public class ProductSpecificationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProductSpecification() throws Exception {
        String payload = """
                {
                  "name": "iPhone 15 Pro",
                  "brand": "Apple",
                  "description": "Titanium design with A17 Pro chip",
                  "productNumber": "IPH15P-256GB",
                  "isBundle": false,
                  "lifecycleStatus": "Active"
                }
                """;

        mockMvc.perform(post("/tmf-api/productCatalogManagement/v4/productSpecification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("iPhone 15 Pro"))
                .andExpect(jsonPath("$.brand").value("Apple"))
                .andExpect(jsonPath("$.isBundle").value(false));
    }
}

