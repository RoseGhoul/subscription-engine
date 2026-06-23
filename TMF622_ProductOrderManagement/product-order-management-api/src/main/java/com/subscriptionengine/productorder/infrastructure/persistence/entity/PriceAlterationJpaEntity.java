package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product_order_price_alteration")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceAlterationJpaEntity {

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

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "application_duration")
    private Integer applicationDuration;

    @Column(name = "recurring_charge_period")
    private String recurringChargePeriod;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Embedded
    private PriceJpaEntity price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_price_id")
    private OrderPriceJpaEntity orderPrice;
}
