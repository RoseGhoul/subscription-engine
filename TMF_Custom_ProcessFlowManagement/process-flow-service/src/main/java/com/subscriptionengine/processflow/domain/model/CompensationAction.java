package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
@Data
public class CompensationAction {
    private String name;
    private String targetApi;
    private String payloadTemplate;
}
