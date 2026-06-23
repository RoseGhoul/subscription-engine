package com.subscriptionengine.payment.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class PaymentJpaEntity extends com.subscriptionengine.payment.infrastructure.persistence.audit.AuditableEntity {

    @Id
    private String id;
    
    private String authorizationCode;
    private String correlatorId;
    private String description;
    private String name;
    private OffsetDateTime paymentDate;
    private String status;
    private Float amountValue;
    private String amountUnit;
}
