package com.subscriptionengine.usagemanagement.domain.model;

public class UsageSpecificationRef {

    private String id;
    private String href;
    private String name;

    public UsageSpecificationRef() {
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
