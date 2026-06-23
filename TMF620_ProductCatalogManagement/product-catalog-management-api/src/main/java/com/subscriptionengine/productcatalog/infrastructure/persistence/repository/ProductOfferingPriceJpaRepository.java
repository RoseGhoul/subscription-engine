package com.subscriptionengine.productcatalog.infrastructure.persistence.repository;

import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingPriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferingPriceJpaRepository extends JpaRepository<ProductOfferingPriceJpaEntity, String>, JpaSpecificationExecutor<ProductOfferingPriceJpaEntity> {
}
