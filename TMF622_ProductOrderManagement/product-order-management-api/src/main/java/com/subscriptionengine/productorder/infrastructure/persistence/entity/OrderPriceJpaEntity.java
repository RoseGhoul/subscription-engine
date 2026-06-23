package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product_order_price")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPriceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price_type")
    private String priceType;

    @Column(name = "recurring_charge_period")
    private String recurringChargePeriod;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    // Embedded Price
    @Embedded
    private PriceJpaEntity price;

    // Nested Alterations
    @OneToMany(mappedBy = "orderPrice", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<PriceAlterationJpaEntity> priceAlteration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrderJpaEntity productOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_item_id")
    private ProductOrderItemJpaEntity productOrderItem;
    
    @Column(name = "item_price_type") // e.g. "ITEM_PRICE" or "ITEM_TOTAL_PRICE"
    private String itemPriceType;
}
