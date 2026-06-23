package com.subscriptionengine.productinventory.infrastructure.event;

import com.subscriptionengine.productinventory.domain.event.ProductEvent;
import com.subscriptionengine.productinventory.domain.port.ProductEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SpringEventPublisherAdapter implements ProductEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(SpringEventPublisherAdapter.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(ProductEvent event) {
        log.info("Publishing domain event: {} for Product ID: {}", event.getEventType(), event.getProduct().getId());
        applicationEventPublisher.publishEvent(event);
    }
}
