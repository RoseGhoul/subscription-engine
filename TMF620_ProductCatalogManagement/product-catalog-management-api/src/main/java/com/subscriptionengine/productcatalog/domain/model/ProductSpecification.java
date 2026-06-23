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
public class ProductSpecification {
    private String id;
    private String href;
    private String name;
    private String brand;
    private String description;
    private String productNumber;
    private Boolean isBundle;
    private String version;
    private String lifecycleStatus;
    private Instant lastUpdate;
    private TimePeriod validFor;
}
