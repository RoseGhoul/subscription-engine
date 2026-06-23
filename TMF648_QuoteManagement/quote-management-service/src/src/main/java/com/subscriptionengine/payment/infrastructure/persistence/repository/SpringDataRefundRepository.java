package com.subscriptionengine.payment.infrastructure.persistence.repository;

import com.subscriptionengine.payment.infrastructure.persistence.entity.RefundJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRefundRepository extends JpaRepository<RefundJpaEntity, String> {
}
