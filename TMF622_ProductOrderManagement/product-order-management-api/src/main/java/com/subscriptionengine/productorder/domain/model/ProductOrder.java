package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    private UUID id;
    private String href;
    private String externalId;
    private String description;
    private String category;
    private String priority;
    
    private OffsetDateTime orderDate;
    private OffsetDateTime requestedStartDate;
    private OffsetDateTime requestedCompletionDate;
    private OffsetDateTime expectedCompletionDate;
    private OffsetDateTime completionDate;
    private OffsetDateTime cancellationDate;
    private String cancellationReason;
    
    private String state;
    
    private String notificationContact;

    private String billingAccountHref;
    private String billingAccountId;
    private String billingAccountName;

    private List<ProductOrderItem> productOrderItem;

    private List<RelatedPartyRef> relatedParty;
    private List<Note> note;

    private List<OrderPrice> orderTotalPrice;

    // We'll add remaining properties in upcoming phases.
}
