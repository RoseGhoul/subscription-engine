package com.subscriptionengine.processflow.application.mapper;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import com.subscriptionengine.processflow.domain.model.ProcessStepExecution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessExecutionMapper {
    ProcessExecution dtoToDomain(org.openapitools.model.ProcessExecutionCreate dto);
    ProcessExecution dtoToDomain(org.openapitools.model.ProcessExecution dto);
    
    @Mapping(target = "state", source = "state")
    org.openapitools.model.ProcessExecution domainToDto(ProcessExecution domain);
    
    ProcessStepExecution stepDtoToDomain(org.openapitools.model.ProcessStepExecution dto);
    
    @Mapping(target = "state", source = "state")
    org.openapitools.model.ProcessStepExecution stepDomainToDto(ProcessStepExecution domain);
    
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
