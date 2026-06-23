package com.subscriptionengine.usagemanagement.infrastructure.persistence.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usage_record")
@EntityListeners(AuditingEntityListener.class)
public class UsageJpaEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(name = "href")
    private String href;

    @Column(name = "description")
    private String description;

    @Column(name = "usage_date", nullable = false)
    private OffsetDateTime usageDate;

    @Column(name = "usage_type", nullable = false)
    private String usageType;

    @Column(name = "status")
    private String status;

    @ElementCollection
    @CollectionTable(name = "usage_characteristic", joinColumns = @JoinColumn(name = "usage_id"))
    private List<UsageCharacteristicJpaEntity> usageCharacteristic = new ArrayList<>();

    @Embedded
    private UsageSpecificationRefJpaEntity usageSpecification;

    @ElementCollection
    @CollectionTable(name = "usage_related_party", joinColumns = @JoinColumn(name = "usage_id"))
    private List<RelatedPartyJpaEntity> relatedParty = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "usage_rated_product_usage", joinColumns = @JoinColumn(name = "usage_id"))
    private List<RatedProductUsageJpaEntity> ratedProductUsage = new ArrayList<>();

    @Column(name = "at_base_type")
    private String atBaseType;

    @Column(name = "at_schema_location")
    private String atSchemaLocation;

    @Column(name = "at_type")
    private String atType;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private OffsetDateTime lastModifiedDate;

    @Version
    @Column(name = "version")
    private Long version;

    public UsageJpaEntity() {
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

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<UsageCharacteristicJpaEntity> getUsageCharacteristic() {
        return usageCharacteristic;
    }

    public void setUsageCharacteristic(List<UsageCharacteristicJpaEntity> usageCharacteristic) {
        if (this.usageCharacteristic != null) {
            this.usageCharacteristic.clear();
            if (usageCharacteristic != null) {
                this.usageCharacteristic.addAll(usageCharacteristic);
            }
        } else {
            this.usageCharacteristic = usageCharacteristic;
        }
    }

    public UsageSpecificationRefJpaEntity getUsageSpecification() {
        return usageSpecification;
    }

    public void setUsageSpecification(UsageSpecificationRefJpaEntity usageSpecification) {
        this.usageSpecification = usageSpecification;
    }

    public List<RelatedPartyJpaEntity> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedPartyJpaEntity> relatedParty) {
        if (this.relatedParty != null) {
            this.relatedParty.clear();
            if (relatedParty != null) {
                this.relatedParty.addAll(relatedParty);
            }
        } else {
            this.relatedParty = relatedParty;
        }
    }

    public List<RatedProductUsageJpaEntity> getRatedProductUsage() {
        return ratedProductUsage;
    }

    public void setRatedProductUsage(List<RatedProductUsageJpaEntity> ratedProductUsage) {
        if (this.ratedProductUsage != null) {
            this.ratedProductUsage.clear();
            if (ratedProductUsage != null) {
                this.ratedProductUsage.addAll(ratedProductUsage);
            }
        } else {
            this.ratedProductUsage = ratedProductUsage;
        }
    }
}
