package com.subscriptionengine.communication.infrastructure.adapter.out.persistence.mapper;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import com.subscriptionengine.communication.infrastructure.adapter.out.persistence.entity.CommunicationMessageJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommunicationMessagePersistenceMapper {
    CommunicationMessageJpaEntity toEntity(CommunicationMessage domain);
    CommunicationMessage toDomain(CommunicationMessageJpaEntity entity);
}
