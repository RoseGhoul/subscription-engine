package com.subscriptionengine.productcatalog.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "product_offering")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingJpaEntity {

    @Id
    private String id;

    private String href;
    private String name;
    private String description;
    private Boolean isBundle;
    private Boolean isSellable;
    private String statusReason;
    
    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;
    
    @LastModifiedDate
    private Instant lastModifiedDate;

    private Instant lastUpdate;
    private String lifecycleStatus;
    
    @Version
    private Long version;

    @Embedded
    private TimePeriodEmbeddable validFor;

    @ElementCollection
    @CollectionTable(name = "product_offering_category", joinColumns = @JoinColumn(name = "product_offering_id"))
    private List<CategoryRefEmbeddable> category;

    @Embedded
    private ProductSpecificationRefEmbeddable productSpecification;

    @ElementCollection
    @CollectionTable(name = "product_offering_price_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
    private List<ProductOfferingPriceRefEmbeddable> productOfferingPrice;

    @ElementCollection
    @CollectionTable(name = "product_offering_bundle", joinColumns = @JoinColumn(name = "product_offering_id"))
    private List<BundledProductOfferingEmbeddable> bundledProductOffering;
}
