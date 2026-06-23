package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.mapper;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import com.subscriptionengine.processflow.domain.model.ProcessStep;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessFlowJpaEntity;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.entity.ProcessStepJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessFlowPersistenceMapper {
    ProcessFlowJpaEntity domainToEntity(ProcessFlow domain);
    ProcessFlow entityToDomain(ProcessFlowJpaEntity entity);
    
    @Mapping(target = "maxRetries", source = "retryInformation.maxRetries")
    @Mapping(target = "currentRetries", source = "retryInformation.currentRetries")
    @Mapping(target = "nextRetryTime", source = "retryInformation.nextRetryTime")
    @Mapping(target = "backoffStrategy", source = "retryInformation.backoffStrategy")
    @Mapping(target = "compName", source = "compensationAction.name")
    @Mapping(target = "compTargetApi", source = "compensationAction.targetApi")
    @Mapping(target = "compPayloadTemplate", source = "compensationAction.payloadTemplate")
    ProcessStepJpaEntity stepDomainToEntity(ProcessStep domain);
    
    @Mapping(target = "retryInformation.maxRetries", source = "maxRetries")
    @Mapping(target = "retryInformation.currentRetries", source = "currentRetries")
    @Mapping(target = "retryInformation.nextRetryTime", source = "nextRetryTime")
    @Mapping(target = "retryInformation.backoffStrategy", source = "backoffStrategy")
    @Mapping(target = "compensationAction.name", source = "compName")
    @Mapping(target = "compensationAction.targetApi", source = "compTargetApi")
    @Mapping(target = "compensationAction.payloadTemplate", source = "compPayloadTemplate")
    ProcessStep stepEntityToDomain(ProcessStepJpaEntity entity);
}
