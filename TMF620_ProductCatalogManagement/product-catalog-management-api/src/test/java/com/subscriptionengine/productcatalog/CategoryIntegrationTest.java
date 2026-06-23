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
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateCategory() throws Exception {
        String payload = """
                {
                  "name": "Mobile Plans",
                  "description": "All postpaid and prepaid plans",
                  "isRoot": true,
                  "lifecycleStatus": "Active"
                }
                """;

        mockMvc.perform(post("/tmf-api/productCatalogManagement/v4/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Mobile Plans"))
                .andExpect(jsonPath("$.isRoot").value(true));
    }
}

