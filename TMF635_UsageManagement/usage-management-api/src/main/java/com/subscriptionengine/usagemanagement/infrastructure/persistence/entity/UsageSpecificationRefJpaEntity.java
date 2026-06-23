package com.subscriptionengine.usagemanagement.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsageSpecificationRefJpaEntity {

    @Column(name = "usage_spec_id")
    private String id;

    @Column(name = "usage_spec_href")
    private String href;

    @Column(name = "usage_spec_name")
    private String name;

    public UsageSpecificationRefJpaEntity() {
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
}
