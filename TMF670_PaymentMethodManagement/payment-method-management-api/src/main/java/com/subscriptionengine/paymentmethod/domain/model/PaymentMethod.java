package com.subscriptionengine.paymentmethod.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {
    private String id;
    private String href;
    private String name;
    private String description;
    private String type; // e.g., BankAccount, BankCard
    private String status;
    private String statusDate;

    // Secure Data (PCI-DSS compliant, NO RAW PAN OR CVV)
    private String maskedPan; // e.g., "**** **** **** 1234"
    private String gatewayToken; // e.g., "tok_12345abc"
    private String expiryDate; // e.g., "12/28"
    private String accountHolderName;
}
