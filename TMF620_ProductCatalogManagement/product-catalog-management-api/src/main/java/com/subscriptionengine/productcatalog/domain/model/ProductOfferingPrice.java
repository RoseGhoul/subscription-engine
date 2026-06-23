package com.subscriptionengine.productcatalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingPrice {
    private String id;
    private String href;
    private String name;
    private String description;
    private Boolean isBundle;
    private String version;
    private String lifecycleStatus;
    private Instant lastUpdate;
    private Float percentage;
    private String priceType;
    private Integer recurringChargePeriodLength;
    private String recurringChargePeriodType;
    
    private Money price;
    private Quantity unitOfMeasure;
    private TimePeriod validFor;
}
