package com.subscriptionengine.productcatalog.infrastructure.persistence.repository;

import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferingJpaRepository extends JpaRepository<ProductOfferingJpaEntity, String>, JpaSpecificationExecutor<ProductOfferingJpaEntity> {
}
