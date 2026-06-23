package com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence.repository;

import com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence.entity.PaymentMethodJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodJpaEntity, String>, JpaSpecificationExecutor<PaymentMethodJpaEntity> {
}
