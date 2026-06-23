package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;
@Entity
@Table(name = "process_step")
@Data
public class ProcessStepJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Integer stepOrder;
    private String targetApi;
    // RetryInformation Embedded
    private Integer maxRetries;
    private Integer currentRetries;
    private OffsetDateTime nextRetryTime;
    private String backoffStrategy;
    // CompensationAction Embedded
    private String compName;
    private String compTargetApi;
    private String compPayloadTemplate;
}
