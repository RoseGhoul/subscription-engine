package com.subscriptionengine.productcatalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOffering {
    private String id;
    private String href;
    private String name;
    private String description;
    private Boolean isBundle;
    private Boolean isSellable;
    private String statusReason;
    private String version;
    private String lifecycleStatus;
    private Instant lastUpdate;
    private TimePeriod validFor;

    private List<CategoryRef> category;
    private ProductSpecificationRef productSpecification;
    private List<ProductOfferingPriceRef> productOfferingPrice;
    private List<BundledProductOffering> bundledProductOffering;
}
