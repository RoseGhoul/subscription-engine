package com.subscriptionengine.payment.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {
    private String id;
    private String authorizationCode;
    private String correlatorId;
    private String description;
    private String name;
    private OffsetDateTime refundDate;
    private String status;
    private Float amountValue;
    private String amountUnit;
    
    // Future Expansion:
    // private String paymentId;
    // private String accountId;
    // private String paymentMethodId;
}
