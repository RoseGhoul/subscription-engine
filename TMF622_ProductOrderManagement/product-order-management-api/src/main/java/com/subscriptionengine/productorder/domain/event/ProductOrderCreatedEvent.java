package com.subscriptionengine.productorder.domain.event;

import com.subscriptionengine.productorder.domain.model.ProductOrder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class ProductOrderCreatedEvent {

    private final ProductOrder productOrder;
    private final OffsetDateTime occurredAt;

    public ProductOrderCreatedEvent(ProductOrder productOrder) {
        this.productOrder = productOrder;
        this.occurredAt = OffsetDateTime.now();
    }
}
