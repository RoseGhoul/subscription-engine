package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceAlteration {
    private String name;
    private String description;
    private String priceType;
    private Integer priority;
    private Integer applicationDuration;
    private String recurringChargePeriod;
    private String unitOfMeasure;
    
    private Price price;
}
