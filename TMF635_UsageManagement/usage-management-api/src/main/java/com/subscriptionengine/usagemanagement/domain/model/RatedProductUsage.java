package com.subscriptionengine.usagemanagement.domain.model;

import java.time.OffsetDateTime;

public class RatedProductUsage {

    private Boolean isBilled;
    private Boolean isTaxExempt;
    private String offerTariffType;
    private String ratingAmountType;
    private OffsetDateTime ratingDate;
    private Float taxRate;
    private String usageRatingTag;
    
    private Money taxExcludedRatingAmount;
    private Money taxIncludedRatingAmount;
    
    private ProductRef productRef;

    public RatedProductUsage() {
    }

    public Boolean getIsBilled() {
        return isBilled;
    }

    public void setIsBilled(Boolean billed) {
        isBilled = billed;
    }

    public Boolean getIsTaxExempt() {
        return isTaxExempt;
    }

    public void setIsTaxExempt(Boolean taxExempt) {
        isTaxExempt = taxExempt;
    }

    public String getOfferTariffType() {
        return offerTariffType;
    }

    public void setOfferTariffType(String offerTariffType) {
        this.offerTariffType = offerTariffType;
    }

    public String getRatingAmountType() {
        return ratingAmountType;
    }

    public void setRatingAmountType(String ratingAmountType) {
        this.ratingAmountType = ratingAmountType;
    }

    public OffsetDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(OffsetDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public String getUsageRatingTag() {
        return usageRatingTag;
    }

    public void setUsageRatingTag(String usageRatingTag) {
        this.usageRatingTag = usageRatingTag;
    }

    public Money getTaxExcludedRatingAmount() {
        return taxExcludedRatingAmount;
    }

    public void setTaxExcludedRatingAmount(Money taxExcludedRatingAmount) {
        this.taxExcludedRatingAmount = taxExcludedRatingAmount;
    }

    public Money getTaxIncludedRatingAmount() {
        return taxIncludedRatingAmount;
    }

    public void setTaxIncludedRatingAmount(Money taxIncludedRatingAmount) {
        this.taxIncludedRatingAmount = taxIncludedRatingAmount;
    }

    public ProductRef getProductRef() {
        return productRef;
    }

    public void setProductRef(ProductRef productRef) {
        this.productRef = productRef;
    }
}
