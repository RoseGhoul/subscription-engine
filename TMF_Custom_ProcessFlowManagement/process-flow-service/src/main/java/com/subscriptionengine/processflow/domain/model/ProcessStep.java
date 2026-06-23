package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
@Data
public class ProcessStep {
    private String id;
    private String name;
    private String description;
    private Integer stepOrder;
    private String targetApi;
    private RetryInformation retryInformation;
    private CompensationAction compensationAction;
}
