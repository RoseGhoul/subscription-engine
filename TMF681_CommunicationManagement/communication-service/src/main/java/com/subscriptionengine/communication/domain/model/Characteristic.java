package com.subscriptionengine.communication.domain.model;

import lombok.Data;

@Data
public class Characteristic {
    private String name;
    private String value;
    private String valueType;
    private String baseType;
    private String schemaLocation;
    private String type;
}
