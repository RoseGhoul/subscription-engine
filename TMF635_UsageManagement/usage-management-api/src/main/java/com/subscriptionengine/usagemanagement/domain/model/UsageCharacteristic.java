package com.subscriptionengine.usagemanagement.domain.model;

public class UsageCharacteristic {

    private String name;
    private String valueType;
    private String value;

    public UsageCharacteristic() {
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
