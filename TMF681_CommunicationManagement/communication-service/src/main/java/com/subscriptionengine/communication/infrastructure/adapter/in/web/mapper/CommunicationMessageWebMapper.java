package com.subscriptionengine.communication.infrastructure.adapter.in.web.mapper;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.openapitools.model.CommunicationMessageCreate;
import org.openapitools.model.CommunicationMessageUpdate;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommunicationMessageWebMapper {
    CommunicationMessage toDomain(CommunicationMessageCreate create);
    CommunicationMessage toDomain(CommunicationMessageUpdate update);
    org.openapitools.model.CommunicationMessage toDto(CommunicationMessage domain);
}
