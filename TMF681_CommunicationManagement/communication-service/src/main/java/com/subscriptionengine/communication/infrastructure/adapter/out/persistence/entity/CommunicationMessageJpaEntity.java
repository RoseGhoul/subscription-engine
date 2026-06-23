package com.subscriptionengine.communication.infrastructure.adapter.out.persistence.entity;

import com.subscriptionengine.communication.domain.model.CommunicationChannel;
import com.subscriptionengine.communication.domain.model.CommunicationTemplateRef;
import com.subscriptionengine.communication.domain.model.Characteristic;
import com.subscriptionengine.communication.domain.model.Receiver;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "communication_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CommunicationMessageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String href;
    private String description;
    private OffsetDateTime sendTime;
    private String state;
    private String subject;
    private String content;
    
    @Enumerated(EnumType.STRING)
    private CommunicationChannel channel;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Receiver> receiver;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private CommunicationTemplateRef template;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Characteristic> characteristic;
    
    private String baseType;
    private String schemaLocation;
    private String type;
    
    @CreatedDate
    private OffsetDateTime createdDate;
    
    @LastModifiedDate
    private OffsetDateTime lastModifiedDate;
    
    @Version
    private Long versionLock;
}
