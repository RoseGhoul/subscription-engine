package com.subscriptionengine.usagemanagement.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsageCharacteristicJpaEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "value_type")
    private String valueType;

    @Column(name = "char_value")
    private String value;

    public UsageCharacteristicJpaEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
