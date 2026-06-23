package com.subscriptionengine.usagemanagement.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RelatedPartyJpaEntity {

    @Column(name = "party_id")
    private String id;

    @Column(name = "party_href")
    private String href;

    @Column(name = "party_name")
    private String name;

    @Column(name = "party_role")
    private String role;

    @Column(name = "at_referred_type")
    private String atReferredType;

    public RelatedPartyJpaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAtReferredType() {
        return atReferredType;
    }

    public void setAtReferredType(String atReferredType) {
        this.atReferredType = atReferredType;
    }
}
