package com.subscriptionengine.processflow.application.mapper;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import com.subscriptionengine.processflow.domain.model.ProcessStep;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessFlowMapper {
    ProcessFlow dtoToDomain(org.openapitools.model.ProcessFlowCreate dto);
    ProcessFlow dtoToDomain(org.openapitools.model.ProcessFlow dto);
    org.openapitools.model.ProcessFlow domainToDto(ProcessFlow domain);
    
    ProcessStep stepDtoToDomain(org.openapitools.model.ProcessStep dto);
    org.openapitools.model.ProcessStep stepDomainToDto(ProcessStep domain);
}
