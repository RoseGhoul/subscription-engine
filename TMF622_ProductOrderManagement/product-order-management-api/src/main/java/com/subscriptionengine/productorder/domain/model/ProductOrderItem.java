package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderItem {
    private String id;
    private Integer quantity;
    private String action; // add, modify, delete, noChange
    private String state;

    // References to ProductOffering (TMF620)
    private String productOfferingHref;
    private String productOfferingId;
    private String productOfferingName;

    private java.util.List<OrderPrice> itemPrice;
    private java.util.List<OrderPrice> itemTotalPrice;
}
