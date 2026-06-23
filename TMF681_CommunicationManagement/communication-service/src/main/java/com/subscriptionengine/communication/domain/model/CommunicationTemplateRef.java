package com.subscriptionengine.communication.domain.model;

import lombok.Data;

@Data
public class CommunicationTemplateRef {
    private String id;
    private String href;
    private String name;
    private String version;
    private String referredType;
}
