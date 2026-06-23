package com.subscriptionengine.usagemanagement.domain.event;

import com.subscriptionengine.usagemanagement.domain.model.Usage;

public interface UsageEventPublisher {
    void publishUsageCreatedEvent(Usage usage);
    void publishUsageStateChangeEvent(Usage usage);
}
