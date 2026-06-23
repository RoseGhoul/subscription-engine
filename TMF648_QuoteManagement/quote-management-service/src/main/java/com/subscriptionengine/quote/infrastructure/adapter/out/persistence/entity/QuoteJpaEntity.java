package com.subscriptionengine.quote.infrastructure.adapter.out.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.util.List;
@Entity
@Table(name = "quote")
@Data
@EntityListeners(AuditingEntityListener.class)
public class QuoteJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String href;
    private String description;
    private String category;
    private String state;
    private Instant quoteDate;
    private Instant effectiveQuoteDate;
    private Instant expectedFulfillmentStartDate;
    private String version;
    private Instant validForStartDateTime;
    private Instant validForEndDateTime;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quote_id")
    private List<QuoteItemJpaEntity> quoteItem;
    
    @Version
    private Long versionLock;
    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
}
