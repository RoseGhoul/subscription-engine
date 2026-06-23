package com.subscriptionengine.productinventory.domain.port;

import com.subscriptionengine.productinventory.domain.event.ProductEvent;

public interface ProductEventPublisher {
    void publish(ProductEvent event);
}
