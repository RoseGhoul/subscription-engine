package com.subscriptionengine.quote.domain.model;
import lombok.Data;
@Data
public class QuotePrice {
    private String name;
    private String description;
    private String priceType;
    private String recurringChargePeriod;
    private Price price;
}
