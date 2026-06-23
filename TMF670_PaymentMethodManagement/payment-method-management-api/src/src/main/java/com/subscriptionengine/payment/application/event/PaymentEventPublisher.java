package com.subscriptionengine.payment.application.event;

import com.subscriptionengine.payment.domain.model.Payment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishPaymentCreatedEvent(Payment payment) {
        log.info("Publishing PaymentCreatedEvent for Payment ID: {}", payment.getId());
        // In a real implementation, wrap in an Event payload (e.g. org.openapitools.model.PaymentCreateEvent)
        applicationEventPublisher.publishEvent(payment);
    }
}
