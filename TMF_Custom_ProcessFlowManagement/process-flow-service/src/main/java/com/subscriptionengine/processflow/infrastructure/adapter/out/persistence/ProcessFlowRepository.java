package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessFlowJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ProcessFlowRepository extends JpaRepository<ProcessFlowJpaEntity, String>, JpaSpecificationExecutor<ProcessFlowJpaEntity> {
}
