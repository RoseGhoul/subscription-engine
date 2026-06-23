package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.mapper;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import com.subscriptionengine.processflow.domain.model.ProcessStepExecution;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessExecutionJpaEntity;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessStepExecutionJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessExecutionPersistenceMapper {
    ProcessExecutionJpaEntity domainToEntity(ProcessExecution domain);
    ProcessExecution entityToDomain(ProcessExecutionJpaEntity entity);
    
    @Mapping(target = "maxRetries", source = "retryInformation.maxRetries")
    @Mapping(target = "currentRetries", source = "retryInformation.currentRetries")
    @Mapping(target = "nextRetryTime", source = "retryInformation.nextRetryTime")
    @Mapping(target = "backoffStrategy", source = "retryInformation.backoffStrategy")
    ProcessStepExecutionJpaEntity stepDomainToEntity(ProcessStepExecution domain);
    
    @Mapping(target = "retryInformation.maxRetries", source = "maxRetries")
    @Mapping(target = "retryInformation.currentRetries", source = "currentRetries")
    @Mapping(target = "retryInformation.nextRetryTime", source = "nextRetryTime")
    @Mapping(target = "retryInformation.backoffStrategy", source = "backoffStrategy")
    ProcessStepExecution stepEntityToDomain(ProcessStepExecutionJpaEntity entity);
    
    default String map(com.subscriptionengine.processflow.domain.model.ProcessExecutionState state) {
        return state != null ? state.name() : null;
    }
    default String map(com.subscriptionengine.processflow.domain.model.ProcessStepState state) {
        return state != null ? state.name() : null;
    }
    default com.subscriptionengine.processflow.domain.model.ProcessExecutionState mapExecutionState(String state) {
        return state != null ? com.subscriptionengine.processflow.domain.model.ProcessExecutionState.valueOf(state) : null;
    }
    default com.subscriptionengine.processflow.domain.model.ProcessStepState mapStepState(String state) {
        return state != null ? com.subscriptionengine.processflow.domain.model.ProcessStepState.valueOf(state) : null;
    }
}
