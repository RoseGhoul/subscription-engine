package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;
@Entity
@Table(name = "process_step_execution")
@Data
public class ProcessStepExecutionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String stepId;
    private String state;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String errorMessage;
    
    private Integer maxRetries;
    private Integer currentRetries;
    private OffsetDateTime nextRetryTime;
    private String backoffStrategy;
}
