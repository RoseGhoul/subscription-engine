package com.subscriptionengine.productinventory.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name = "product_record")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ProductJpaEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "product_serial_number")
    private String productSerialNumber;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "termination_date")
    private OffsetDateTime terminationDate;

    @Column(name = "status")
    private String status;

    @Column(name = "is_bundle")
    private Boolean isBundle;

    @Column(name = "is_customer_visible")
    private Boolean isCustomerVisible;

    @Column(name = "product_offering_id")
    private String productOfferingId;

    @Column(name = "product_offering_name")
    private String productOfferingName;

    @Column(name = "product_specification_id")
    private String productSpecificationId;

    @Column(name = "product_specification_name")
    private String productSpecificationName;

    @Column(name = "product_specification_version")
    private String productSpecificationVersion;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_characteristic", joinColumns = @JoinColumn(name = "product_id"))
    private java.util.List<ProductCharacteristicEmbeddable> productCharacteristic;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_price", joinColumns = @JoinColumn(name = "product_id"))
    private java.util.List<ProductPriceEmbeddable> productPrice;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;

    @Embeddable
    @Getter
    @Setter
    public static class ProductCharacteristicEmbeddable {
        @Column(name = "name")
        private String name;
        @Column(name = "value_type")
        private String valueType;
        @Column(name = "char_value")
        private String value;
    }

    @Embeddable
    @Getter
    @Setter
    public static class ProductPriceEmbeddable {
        @Column(name = "name")
        private String name;
        @Column(name = "description", length = 1000)
        private String description;
        @Column(name = "price_type")
        private String priceType;
        @Column(name = "recurring_charge_period")
        private String recurringChargePeriod;
        @Column(name = "unit_of_measure")
        private String unitOfMeasure;
        @Column(name = "price_value")
        private Float priceValue;
        @Column(name = "price_unit")
        private String priceUnit;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_relationship", joinColumns = @JoinColumn(name = "product_id"))
    private java.util.List<ProductRelationshipEmbeddable> productRelationship;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_place", joinColumns = @JoinColumn(name = "product_id"))
    private java.util.List<PlaceEmbeddable> place;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_related_party", joinColumns = @JoinColumn(name = "product_id"))
    private java.util.List<RelatedPartyEmbeddable> relatedParty;

    @Embeddable
    @Getter
    @Setter
    public static class ProductRelationshipEmbeddable {
        @Column(name = "relationship_type")
        private String relationshipType;
        @Column(name = "rel_product_id")
        private String productId;
        @Column(name = "rel_product_href")
        private String productHref;
        @Column(name = "rel_product_name")
        private String productName;
    }

    @Embeddable
    @Getter
    @Setter
    public static class PlaceEmbeddable {
        @Column(name = "place_id")
        private String id;
        @Column(name = "place_href")
        private String href;
        @Column(name = "place_name")
        private String name;
        @Column(name = "place_role")
        private String role;
    }

    @Embeddable
    @Getter
    @Setter
    public static class RelatedPartyEmbeddable {
        @Column(name = "party_id")
        private String id;
        @Column(name = "party_href")
        private String href;
        @Column(name = "party_name")
        private String name;
        @Column(name = "party_role")
        private String role;
    }
}
