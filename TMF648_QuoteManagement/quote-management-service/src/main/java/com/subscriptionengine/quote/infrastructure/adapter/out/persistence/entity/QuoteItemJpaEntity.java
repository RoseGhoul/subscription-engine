package com.subscriptionengine.quote.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "quote_item")
@Data
public class QuoteItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String action;
    private Float quantityAmount;
    private String quantityUnits;
    private String productOfferingId;
    private String productOfferingHref;
    private String productOfferingName;
}
