package com.subscriptionengine.usagemanagement.infrastructure.messaging.publisher;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import com.subscriptionengine.usagemanagement.domain.event.UsageEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaUsageEventPublisher implements UsageEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaUsageEventPublisher.class);

    @Override
    public void publishUsageCreatedEvent(Usage usage) {
        log.info("Publishing UsageCreatedEvent for usage id: {}", usage.getId());
        // TODO: Implement actual Kafka publishing logic in Phase 9
    }

    @Override
    public void publishUsageStateChangeEvent(Usage usage) {
        log.info("Publishing UsageStateChangeEvent for usage id: {}, new state: {}", usage.getId(), usage.getStatus());
        // TODO: Implement actual Kafka publishing logic in Phase 9
    }
}
