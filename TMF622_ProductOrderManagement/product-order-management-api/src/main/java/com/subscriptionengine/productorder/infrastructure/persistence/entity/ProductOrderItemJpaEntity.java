package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product_order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderItemJpaEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "action")
    private String action;

    @Column(name = "state")
    private String state;

    @Column(name = "product_offering_href")
    private String productOfferingHref;

    @Column(name = "product_offering_id")
    private String productOfferingId;

    @Column(name = "product_offering_name")
    private String productOfferingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrderJpaEntity productOrder;

    @OneToMany(mappedBy = "productOrderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderPriceJpaEntity> itemPrice;

    @OneToMany(mappedBy = "productOrderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderPriceJpaEntity> itemTotalPrice;
}
