package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
import java.util.List;
import java.util.Map;
@Data
public class ProcessExecution {
    private String id;
    private String href;
    private String processFlowId;
    private ProcessExecutionState state;
    private java.time.OffsetDateTime startTime;
    private java.time.OffsetDateTime endTime;
    private Map<String, Object> processContext;
    private List<ProcessStepExecution> processStepExecution;
}
