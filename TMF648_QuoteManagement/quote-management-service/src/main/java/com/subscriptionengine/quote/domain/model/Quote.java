package com.subscriptionengine.quote.domain.model;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Quote {
    private String id;
    private String href;
    private String description;
    private String category;
    private String state;
    private OffsetDateTime quoteDate;
    private OffsetDateTime effectiveQuoteDate;
    private OffsetDateTime expectedFulfillmentStartDate;
    private String version;
    private TimePeriod validFor;
    private List<QuoteItem> quoteItem;
    private List<RelatedParty> relatedParty;
    private List<QuotePrice> quoteTotalPrice;
    private List<Note> note;
    private List<AgreementRef> agreement;
    private List<BillingAccountRef> billingAccount;
}
