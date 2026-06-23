package com.subscriptionengine.processflow.application.port.out.event;
import com.subscriptionengine.processflow.domain.event.ProcessExecutionStateChangeEvent;
public interface DomainEventPublisher {
    void publishProcessExecutionStateChange(ProcessExecutionStateChangeEvent event);
}
