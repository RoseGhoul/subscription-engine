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
public class ProductOfferingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProductOffering() throws Exception {
        String payload = """
                {
                  "name": "Super Fast Internet",
                  "description": "Gigabit internet package",
                  "isBundle": true,
                  "isSellable": true,
                  "statusReason": "Launched successfully",
                  "lifecycleStatus": "Active",
                  "category": [
                    {
                      "id": "cat-1",
                      "name": "Internet Plans"
                    }
                  ],
                  "productSpecification": {
                    "id": "spec-1",
                    "name": "Gigabit Spec"
                  },
                  "productOfferingPrice": [
                    {
                      "id": "pop-1",
                      "name": "Monthly Fee"
                    }
                  ],
                  "bundledProductOffering": [
                    {
                      "id": "bnd-1",
                      "name": "Free Router"
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/tmf-api/productCatalogManagement/v4/productOffering")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Super Fast Internet"))
                .andExpect(jsonPath("$.isBundle").value(true))
                .andExpect(jsonPath("$.category[0].id").value("cat-1"))
                .andExpect(jsonPath("$.category[0].name").value("Internet Plans"))
                .andExpect(jsonPath("$.productSpecification.id").value("spec-1"))
                .andExpect(jsonPath("$.productSpecification.name").value("Gigabit Spec"))
                .andExpect(jsonPath("$.productOfferingPrice[0].id").value("pop-1"))
                .andExpect(jsonPath("$.productOfferingPrice[0].name").value("Monthly Fee"))
                .andExpect(jsonPath("$.bundledProductOffering[0].id").value("bnd-1"))
                .andExpect(jsonPath("$.bundledProductOffering[0].name").value("Free Router"));
    }
}

