package com.subscriptionengine.productorder.application.port.out;

import com.subscriptionengine.productorder.domain.event.ProductOrderCreatedEvent;
import com.subscriptionengine.productorder.domain.event.ProductOrderStateChangedEvent;

public interface EventPublisher {
    void publishProductOrderCreatedEvent(ProductOrderCreatedEvent event);
    void publishProductOrderStateChangedEvent(ProductOrderStateChangedEvent event);
}
