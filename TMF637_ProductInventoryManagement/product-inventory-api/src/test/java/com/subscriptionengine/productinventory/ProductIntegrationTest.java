package com.subscriptionengine.productinventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.model.ProductCreate;
import org.openapitools.model.ProductUpdate;
import org.openapitools.model.ProductStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateRetrieveAndPatchProduct() throws Exception {
        // 1. Create Product
        ProductCreate createDto = new ProductCreate();
        createDto.setName("Integration Test Product");
        createDto.setDescription("Product created via test");
        createDto.setProductSerialNumber("TEST-SN-001");
        createDto.setStatus(ProductStatusType.CREATED);

        String createResponse = mockMvc.perform(post("/tmf-api/productInventoryManagement/v4/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Integration Test Product"))
                .andReturn().getResponse().getContentAsString();

        String id = com.jayway.jsonpath.JsonPath.read(createResponse, "$.id");

        // 2. Retrieve Product
        mockMvc.perform(get("/tmf-api/productInventoryManagement/v4/product/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test Product"));

        // 3. Patch Product
        ProductUpdate updateDto = new ProductUpdate();
        updateDto.setStatus(ProductStatusType.ACTIVE);

        mockMvc.perform(patch("/tmf-api/productInventoryManagement/v4/product/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("active"));
                
        // 4. Duplicate SN check (Validation Phase 6 test)
        ProductCreate duplicateDto = new ProductCreate();
        duplicateDto.setName("Duplicate SN Product");
        duplicateDto.setProductSerialNumber("TEST-SN-001");
        duplicateDto.setStatus(ProductStatusType.CREATED);

        mockMvc.perform(post("/tmf-api/productInventoryManagement/v4/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("productSerialNumber must be unique"));
                
        // 5. List Products with Pagination and Filtering (Phase 8 test)
        mockMvc.perform(get("/tmf-api/productInventoryManagement/v4/product?name=Integration Test&status=active&offset=0&limit=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Integration Test Product"))
                .andExpect(header().exists("X-Total-Count"))
                .andExpect(header().exists("X-Result-Count"));
                
        // 6. Delete Product
        mockMvc.perform(delete("/tmf-api/productInventoryManagement/v4/product/" + id))
                .andExpect(status().isNoContent());
    }
}

