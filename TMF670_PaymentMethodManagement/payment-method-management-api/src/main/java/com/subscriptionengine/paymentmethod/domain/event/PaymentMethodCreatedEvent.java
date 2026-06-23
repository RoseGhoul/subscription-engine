package com.subscriptionengine.paymentmethod.domain.event;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
public class PaymentMethodCreatedEvent {
    private final String eventId;
    private final OffsetDateTime eventTime;
    private final PaymentMethod payload;
}
