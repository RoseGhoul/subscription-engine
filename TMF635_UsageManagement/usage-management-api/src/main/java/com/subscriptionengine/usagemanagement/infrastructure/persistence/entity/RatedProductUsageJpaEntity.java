package com.subscriptionengine.usagemanagement.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.OffsetDateTime;

@Embeddable
public class RatedProductUsageJpaEntity {

    @Column(name = "is_billed")
    private Boolean isBilled;

    @Column(name = "is_tax_exempt")
    private Boolean isTaxExempt;

    @Column(name = "offer_tariff_type")
    private String offerTariffType;

    @Column(name = "rating_amount_type")
    private String ratingAmountType;

    @Column(name = "rating_date")
    private OffsetDateTime ratingDate;

    @Column(name = "tax_rate")
    private Float taxRate;

    @Column(name = "usage_rating_tag")
    private String usageRatingTag;

    @Column(name = "tax_excluded_amount_unit")
    private String taxExcludedRatingAmountUnit;

    @Column(name = "tax_excluded_amount_value")
    private Float taxExcludedRatingAmountValue;

    @Column(name = "tax_included_amount_unit")
    private String taxIncludedRatingAmountUnit;

    @Column(name = "tax_included_amount_value")
    private Float taxIncludedRatingAmountValue;

    @Column(name = "product_ref_id")
    private String productRefId;

    @Column(name = "product_ref_href")
    private String productRefHref;

    @Column(name = "product_ref_name")
    private String productRefName;

    public RatedProductUsageJpaEntity() {
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

    public String getTaxExcludedRatingAmountUnit() {
        return taxExcludedRatingAmountUnit;
    }

    public void setTaxExcludedRatingAmountUnit(String taxExcludedRatingAmountUnit) {
        this.taxExcludedRatingAmountUnit = taxExcludedRatingAmountUnit;
    }

    public Float getTaxExcludedRatingAmountValue() {
        return taxExcludedRatingAmountValue;
    }

    public void setTaxExcludedRatingAmountValue(Float taxExcludedRatingAmountValue) {
        this.taxExcludedRatingAmountValue = taxExcludedRatingAmountValue;
    }

    public String getTaxIncludedRatingAmountUnit() {
        return taxIncludedRatingAmountUnit;
    }

    public void setTaxIncludedRatingAmountUnit(String taxIncludedRatingAmountUnit) {
        this.taxIncludedRatingAmountUnit = taxIncludedRatingAmountUnit;
    }

    public Float getTaxIncludedRatingAmountValue() {
        return taxIncludedRatingAmountValue;
    }

    public void setTaxIncludedRatingAmountValue(Float taxIncludedRatingAmountValue) {
        this.taxIncludedRatingAmountValue = taxIncludedRatingAmountValue;
    }

    public String getProductRefId() {
        return productRefId;
    }

    public void setProductRefId(String productRefId) {
        this.productRefId = productRefId;
    }

    public String getProductRefHref() {
        return productRefHref;
    }

    public void setProductRefHref(String productRefHref) {
        this.productRefHref = productRefHref;
    }

    public String getProductRefName() {
        return productRefName;
    }

    public void setProductRefName(String productRefName) {
        this.productRefName = productRefName;
    }
}
