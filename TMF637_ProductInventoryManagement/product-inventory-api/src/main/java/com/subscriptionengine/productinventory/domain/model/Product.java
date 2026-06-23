package com.subscriptionengine.productinventory.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private Long version;
    private String href;
    private String name;
    private String description;
    private String productSerialNumber;
    private OffsetDateTime startDate;
    private OffsetDateTime terminationDate;
    private String status;
    private Boolean isBundle;
    private Boolean isCustomerVisible;

    // References
    private ProductOfferingRef productOffering;
    private ProductSpecificationRef productSpecification;

    // Phase 4 Extensions
    private java.util.List<ProductCharacteristic> productCharacteristic;
    private java.util.List<ProductPrice> productPrice;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductCharacteristic {
        private String name;
        private String valueType;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductPrice {
        private String name;
        private String description;
        private String priceType;
        private String recurringChargePeriod;
        private String unitOfMeasure;
        private Price price;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Price {
            private Float value;
            private String unit;
        }
    }

    // Phase 5 Extensions
    private java.util.List<ProductRelationship> productRelationship;
    private java.util.List<PlaceRef> place;
    private java.util.List<RelatedPartyRef> relatedParty;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRelationship {
        private String relationshipType;
        private String productId;
        private String productHref;
        private String productName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceRef {
        private String id;
        private String href;
        private String name;
        private String role;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedPartyRef {
        private String id;
        private String href;
        private String name;
        private String role;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductOfferingRef {
        private String id;
        private String href;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductSpecificationRef {
        private String id;
        private String href;
        private String name;
        private String version;
    }
}
