package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_order")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    @Column(name = "href")
    private String href;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "priority")
    private String priority;

    @Column(name = "order_date")
    private OffsetDateTime orderDate;

    @Column(name = "requested_start_date")
    private OffsetDateTime requestedStartDate;

    @Column(name = "requested_completion_date")
    private OffsetDateTime requestedCompletionDate;

    @Column(name = "expected_completion_date")
    private OffsetDateTime expectedCompletionDate;

    @Column(name = "completion_date")
    private OffsetDateTime completionDate;

    @Column(name = "cancellation_date")
    private OffsetDateTime cancellationDate;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "state")
    private String state;

    @Column(name = "notification_contact")
    private String notificationContact;

    // Billing Account Embedded
    @Column(name = "billing_account_href")
    private String billingAccountHref;

    @Column(name = "billing_account_id")
    private String billingAccountId;

    @Column(name = "billing_account_name")
    private String billingAccountName;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<ProductOrderItemJpaEntity> productOrderItem;

    @ElementCollection
    @CollectionTable(name = "product_order_related_party", joinColumns = @JoinColumn(name = "product_order_id"))
    private java.util.List<RelatedPartyRefJpaEntity> relatedParty;

    @ElementCollection
    @CollectionTable(name = "product_order_note", joinColumns = @JoinColumn(name = "product_order_id"))
    private java.util.List<NoteJpaEntity> note;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderPriceJpaEntity> orderTotalPrice;


    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
}
