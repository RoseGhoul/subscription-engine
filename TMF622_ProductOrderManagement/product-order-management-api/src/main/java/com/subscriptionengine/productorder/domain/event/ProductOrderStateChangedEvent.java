package com.subscriptionengine.productorder.domain.event;

import com.subscriptionengine.productorder.domain.model.ProductOrder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class ProductOrderStateChangedEvent {

    private final ProductOrder productOrder;
    private final String oldState;
    private final String newState;
    private final OffsetDateTime occurredAt;

    public ProductOrderStateChangedEvent(ProductOrder productOrder, String oldState, String newState) {
        this.productOrder = productOrder;
        this.oldState = oldState;
        this.newState = newState;
        this.occurredAt = OffsetDateTime.now();
    }
}
