package com.subscriptionengine.communication.infrastructure.adapter.out.persistence.repository;

import com.subscriptionengine.communication.infrastructure.adapter.out.persistence.entity.CommunicationMessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationMessageJpaRepository extends JpaRepository<CommunicationMessageJpaEntity, String>, JpaSpecificationExecutor<CommunicationMessageJpaEntity> {}
