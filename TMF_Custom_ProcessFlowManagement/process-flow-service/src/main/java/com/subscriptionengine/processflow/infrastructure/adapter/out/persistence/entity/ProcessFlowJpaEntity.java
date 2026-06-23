package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.OffsetDateTime;
import java.util.List;
@Entity
@Table(name = "process_flow")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProcessFlowJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String href;
    private String name;
    private String description;
    private String version;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "process_flow_id")
    private List<ProcessStepJpaEntity> processStep;
    
    @CreatedDate
    private OffsetDateTime createdDate;
    @LastModifiedDate
    private OffsetDateTime lastModifiedDate;
    @Version
    private Long versionLock;
}
