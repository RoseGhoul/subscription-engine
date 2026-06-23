package com.subscriptionengine.productorder.infrastructure.event;

import com.subscriptionengine.productorder.application.port.out.EventPublisher;
import com.subscriptionengine.productorder.domain.event.ProductOrderCreatedEvent;
import com.subscriptionengine.productorder.domain.event.ProductOrderStateChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisherAdapter implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProductOrderCreatedEvent(ProductOrderCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishProductOrderStateChangedEvent(ProductOrderStateChangedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
