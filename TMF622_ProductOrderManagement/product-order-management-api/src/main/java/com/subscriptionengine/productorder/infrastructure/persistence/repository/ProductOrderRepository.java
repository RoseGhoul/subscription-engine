package com.subscriptionengine.productorder.infrastructure.persistence.repository;

import com.subscriptionengine.productorder.infrastructure.persistence.entity.ProductOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrderJpaEntity, UUID>, JpaSpecificationExecutor<ProductOrderJpaEntity> {
}
