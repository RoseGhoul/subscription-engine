package com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.EntityListeners;

import java.time.Instant;

@Entity
@Table(name = "payment_method")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PaymentMethodJpaEntity {
    
    @Id
    private String id;
    private String href;
    private String name;
    private String description;
    private String type;
    private String status;
    private String statusDate;

    // Secure Data mapped to columns
    private String maskedPan;
    private String gatewayToken;
    private String expiryDate;
    private String accountHolderName;

    @Version
    private Long version;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
