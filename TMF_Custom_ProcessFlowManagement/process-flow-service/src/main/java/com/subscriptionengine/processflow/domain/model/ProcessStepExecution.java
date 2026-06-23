package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
@Data
public class ProcessStepExecution {
    private String id;
    private String stepId;
    private ProcessStepState state;
    private java.time.OffsetDateTime startTime;
    private java.time.OffsetDateTime endTime;
    private String errorMessage;
    private RetryInformation retryInformation;
}
