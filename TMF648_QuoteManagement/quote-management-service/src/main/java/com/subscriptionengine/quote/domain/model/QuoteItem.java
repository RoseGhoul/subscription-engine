package com.subscriptionengine.quote.domain.model;
import java.util.List;
import lombok.Data;
@Data
public class QuoteItem {
    private String id;
    private String action;
    private Quantity quantity;
    private List<QuotePrice> quoteItemPrice;
    private List<Note> note;
    private ProductOfferingRef productOffering;
}
