package com.subscriptionengine.usagemanagement.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usage {

    private UUID id;
    private String href;
    private String description;
    private OffsetDateTime usageDate;
    private String usageType;
    private String status;

    private List<UsageCharacteristic> usageCharacteristic = new ArrayList<>();
    private UsageSpecificationRef usageSpecification;
    
    private List<RelatedParty> relatedParty = new ArrayList<>();
    private List<RatedProductUsage> ratedProductUsage = new ArrayList<>();

    private String atBaseType;
    private String atSchemaLocation;
    private String atType;

    public Usage() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(OffsetDateTime usageDate) {
        this.usageDate = usageDate;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtBaseType() {
        return atBaseType;
    }

    public void setAtBaseType(String atBaseType) {
        this.atBaseType = atBaseType;
    }

    public String getAtSchemaLocation() {
        return atSchemaLocation;
    }

    public void setAtSchemaLocation(String atSchemaLocation) {
        this.atSchemaLocation = atSchemaLocation;
    }

    public String getAtType() {
        return atType;
    }

    public void setAtType(String atType) {
        this.atType = atType;
    }

    public List<UsageCharacteristic> getUsageCharacteristic() {
        return usageCharacteristic;
    }

    public void setUsageCharacteristic(List<UsageCharacteristic> usageCharacteristic) {
        if (usageCharacteristic != null) {
            this.usageCharacteristic = usageCharacteristic;
        }
    }

    public UsageSpecificationRef getUsageSpecification() {
        return usageSpecification;
    }

    public void setUsageSpecification(UsageSpecificationRef usageSpecification) {
        this.usageSpecification = usageSpecification;
    }

    public List<RelatedParty> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedParty> relatedParty) {
        if (relatedParty != null) {
            this.relatedParty = relatedParty;
        }
    }

    public List<RatedProductUsage> getRatedProductUsage() {
        return ratedProductUsage;
    }

    public void setRatedProductUsage(List<RatedProductUsage> ratedProductUsage) {
        if (ratedProductUsage != null) {
            this.ratedProductUsage = ratedProductUsage;
        }
    }
}
