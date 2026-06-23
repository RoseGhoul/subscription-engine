package com.subscriptionengine.productcatalog.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingPriceRefEmbeddable {
    private String priceId;
    private String priceHref;
    private String priceName;
    private String priceVersion;
}
