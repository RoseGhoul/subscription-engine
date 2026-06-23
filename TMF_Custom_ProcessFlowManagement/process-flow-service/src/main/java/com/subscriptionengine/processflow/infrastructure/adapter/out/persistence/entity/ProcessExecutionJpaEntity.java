package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
@Entity
@Table(name = "process_execution")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProcessExecutionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String href;
    private String processFlowId;
    private String state;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> processContext;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "process_execution_id")
    private List<ProcessStepExecutionJpaEntity> processStepExecution;
    
    @CreatedDate
    private OffsetDateTime createdDate;
    @LastModifiedDate
    private OffsetDateTime lastModifiedDate;
    @Version
    private Long versionLock;
}
