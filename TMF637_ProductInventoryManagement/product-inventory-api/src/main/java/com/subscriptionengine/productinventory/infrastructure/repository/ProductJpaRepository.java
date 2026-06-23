package com.subscriptionengine.productinventory.infrastructure.repository;

import com.subscriptionengine.productinventory.infrastructure.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String>, JpaSpecificationExecutor<ProductJpaEntity> {
    java.util.Optional<ProductJpaEntity> findByProductSerialNumber(String productSerialNumber);
}
