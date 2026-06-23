package com.subscriptionengine.productcatalog.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpaEntity {

    @Id
    private String id;

    private String href;
    private String name;
    private String description;
    private Boolean isRoot;
    private String parentId;
    
    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;
    
    @LastModifiedDate
    private Instant lastModifiedDate;

    private Instant lastUpdate;
    private String lifecycleStatus;
    
    @Version
    private Long version;

    @Embedded
    private TimePeriodEmbeddable validFor;
}
