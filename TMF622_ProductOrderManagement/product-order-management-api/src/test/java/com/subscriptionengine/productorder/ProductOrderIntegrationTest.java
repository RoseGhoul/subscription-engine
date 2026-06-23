package com.subscriptionengine.productorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductOrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProductOrderSuccessfully() throws Exception {
        ProductOrderCreate createReq = new ProductOrderCreate();
        createReq.setCategory("B2C");
        createReq.setDescription("New broadband order");
        
        // Add Note
        Note note = new Note("Customer wants installation in the morning.");
        note.setAuthor("System");
        createReq.addNoteItem(note);
        
        // Add RelatedParty
        RelatedParty party = new RelatedParty();
        party.setId("cust-123");
        party.setRole("Customer");
        party.setName("John Doe");
        party.setAtReferredType("Customer");
        createReq.addRelatedPartyItem(party);
        
        // Add ProductOrderItem
        ProductOrderItem item = new ProductOrderItem("item-1", OrderItemActionType.ADD);
        item.setQuantity(1);
        ProductOfferingRef offerRef = new ProductOfferingRef();
        offerRef.setId("offering-999");
        offerRef.setName("Fiber 100Mbps");
        offerRef.setAtReferredType("ProductOffering");
        item.setProductOffering(offerRef);
        createReq.addProductOrderItemItem(item);

        mockMvc.perform(post("/tmf-api/productOrderingManagement/v4/productOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.state").value("acknowledged"))
                .andExpect(jsonPath("$.category").value("B2C"))
                .andExpect(jsonPath("$.productOrderItem[0].id").exists())
                .andExpect(jsonPath("$.productOrderItem[0].state").value("acknowledged"))
                .andExpect(jsonPath("$.productOrderItem[0].productOffering.name").value("Fiber 100Mbps"))
                .andExpect(jsonPath("$.note[0].id").exists())
                .andExpect(jsonPath("$.note[0].text").value("Customer wants installation in the morning."))
                .andExpect(jsonPath("$.relatedParty[0].role").value("Customer"));
    }

    @Test
    void shouldFailValidationWithoutItems() throws Exception {
        ProductOrderCreate createReq = new ProductOrderCreate();
        createReq.setCategory("B2C");

        mockMvc.perform(post("/tmf-api/productOrderingManagement/v4/productOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)))
                .andExpect(status().isBadRequest()); 
    }

    @Test
    void shouldFilterProductOrders() throws Exception {
        // Create B2B order
        ProductOrderCreate b2bReq = new ProductOrderCreate();
        b2bReq.setCategory("B2B");
        b2bReq.setDescription("B2B Order");
        ProductOrderItem b2bItem = new ProductOrderItem("item-b2b", OrderItemActionType.ADD);
        b2bItem.setQuantity(1);
        b2bReq.addProductOrderItemItem(b2bItem);
        
        mockMvc.perform(post("/tmf-api/productOrderingManagement/v4/productOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(b2bReq)))
                .andExpect(status().isCreated());

        // Create B2C order
        ProductOrderCreate b2cReq = new ProductOrderCreate();
        b2cReq.setCategory("B2C");
        b2cReq.setDescription("B2C Order");
        ProductOrderItem b2cItem = new ProductOrderItem("item-b2c", OrderItemActionType.ADD);
        b2cItem.setQuantity(1);
        b2cReq.addProductOrderItemItem(b2cItem);

        mockMvc.perform(post("/tmf-api/productOrderingManagement/v4/productOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(b2cReq)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tmf-api/productOrderingManagement/v4/productOrder?category=B2B"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category").value("B2B"))
                .andExpect(jsonPath("$[0].description").value("B2B Order"));
    }
}
