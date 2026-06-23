package com.subscriptionengine.usagemanagement.domain.model;

public class Money {

    private String unit;
    private Float value;

    public Money() {
    }

    public Money(String unit, Float value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
