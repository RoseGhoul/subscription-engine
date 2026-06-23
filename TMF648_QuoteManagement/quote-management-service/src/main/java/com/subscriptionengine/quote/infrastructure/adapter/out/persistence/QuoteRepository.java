package com.subscriptionengine.quote.infrastructure.adapter.out.persistence;
import com.subscriptionengine.quote.infrastructure.adapter.out.persistence.entity.QuoteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface QuoteRepository extends JpaRepository<QuoteJpaEntity, String>, JpaSpecificationExecutor<QuoteJpaEntity> {
}
