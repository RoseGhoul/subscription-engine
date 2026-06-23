package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.spec;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessExecutionJpaEntity;
import org.springframework.data.jpa.domain.Specification;
public class ProcessExecutionSpecifications {
    public static Specification<ProcessExecutionJpaEntity> hasState(String state) {
        return (root, query, cb) -> state == null ? null : cb.equal(root.get("state"), state);
    }
    public static Specification<ProcessExecutionJpaEntity> hasProcessFlowId(String processFlowId) {
        return (root, query, cb) -> processFlowId == null ? null : cb.equal(root.get("processFlowId"), processFlowId);
    }
}
