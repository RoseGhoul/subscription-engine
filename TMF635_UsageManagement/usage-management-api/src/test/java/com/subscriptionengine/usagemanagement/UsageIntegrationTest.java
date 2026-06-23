package com.subscriptionengine.usagemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUsage() throws Exception {
        String usageJson = """
                {
                  "description": "Data usage for May 2026",
                  "usageType": "Data",
                  "usageDate": "2026-05-15T10:30:00Z",
                  "status": "received",
                  "ratedProductUsage": [
                    {
                      "isBilled": false,
                      "isTaxExempt": true,
                      "taxExcludedRatingAmount": {
                        "unit": "USD",
                        "value": 5.50
                      }
                    }
                  ],
                  "relatedParty": [
                    {
                      "id": "party-123",
                      "name": "John Doe",
                      "role": "Customer",
                      "@referredType": "Individual"
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/tmf-api/usageManagement/v4/usage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usageJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.href").exists())
                .andExpect(jsonPath("$.usageType").value("Data"))
                .andExpect(jsonPath("$.status").value("received"));
    }

    @Test
    void testListUsageWithPagination() throws Exception {
        // Create 2 usage records
        for (int i=0; i<2; i++) {
            mockMvc.perform(post("/tmf-api/usageManagement/v4/usage")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                      {
                        "description": "Data usage for May 2026",
                        "usageType": "Data",
                        "usageDate": "2026-05-15T10:30:00Z",
                        "status": "received",
                        "relatedParty": [
                          {
                            "id": "party-123",
                            "name": "John Doe",
                            "role": "Customer",
                            "@referredType": "Individual"
                          }
                        ]
                      }
                      """))
                    .andExpect(status().isCreated());
        }

        // List with limit 1
        mockMvc.perform(get("/tmf-api/usageManagement/v4/usage?limit=1&offset=0"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Total-Count"))
                .andExpect(header().string("X-Result-Count", "1"))
                .andExpect(jsonPath("$.length()").value(1));
    }
}

