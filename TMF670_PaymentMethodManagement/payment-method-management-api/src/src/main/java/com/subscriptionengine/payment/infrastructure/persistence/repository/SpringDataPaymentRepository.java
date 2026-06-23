package com.subscriptionengine.payment.infrastructure.persistence.repository;

import com.subscriptionengine.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPaymentRepository extends JpaRepository<PaymentJpaEntity, String> {
}
