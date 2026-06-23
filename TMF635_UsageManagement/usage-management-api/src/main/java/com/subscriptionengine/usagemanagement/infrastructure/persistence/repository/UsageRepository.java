package com.subscriptionengine.usagemanagement.infrastructure.persistence.repository;

import com.subscriptionengine.usagemanagement.infrastructure.persistence.entity.UsageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsageRepository extends JpaRepository<UsageJpaEntity, UUID>, JpaSpecificationExecutor<UsageJpaEntity> {
}
