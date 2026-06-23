package com.subscriptionengine.processflow.infrastructure.adapter.out.event;
import com.subscriptionengine.processflow.application.port.out.event.DomainEventPublisher;
import com.subscriptionengine.processflow.domain.event.ProcessExecutionStateChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    public SpringDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Override
    public void publishProcessExecutionStateChange(ProcessExecutionStateChangeEvent event) {
        log.info("Publishing ProcessExecutionStateChangeEvent for Execution ID: {}, New State: {}", event.getProcessExecutionId(), event.getNewState());
        applicationEventPublisher.publishEvent(event);
    }
}
