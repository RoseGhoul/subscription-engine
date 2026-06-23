package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
@Data
public class RetryInformation {
    private Integer maxRetries;
    private Integer currentRetries;
    private java.time.OffsetDateTime nextRetryTime;
    private String backoffStrategy;
}
