package com.subscriptionengine.processflow.domain.event;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import lombok.Value;
import java.time.OffsetDateTime;
@Value
public class ProcessExecutionStateChangeEvent {
    private String processExecutionId;
    private String newState;
    private OffsetDateTime timestamp;
    private ProcessExecution executionSnapshot;
}
