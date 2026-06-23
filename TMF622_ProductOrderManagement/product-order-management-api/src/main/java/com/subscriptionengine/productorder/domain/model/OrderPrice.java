package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPrice {
    private String name;
    private String description;
    private String priceType;
    private String recurringChargePeriod;
    private String unitOfMeasure;
    
    private Price price;
    
    private List<PriceAlteration> priceAlteration;
}
