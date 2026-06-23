package com.subscriptionengine.communication.infrastructure.adapter.out.persistence;

import com.subscriptionengine.communication.application.port.out.CommunicationMessageRepositoryPort;
import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import com.subscriptionengine.communication.infrastructure.adapter.out.persistence.entity.CommunicationMessageJpaEntity;
import com.subscriptionengine.communication.infrastructure.adapter.out.persistence.mapper.CommunicationMessagePersistenceMapper;
import com.subscriptionengine.communication.infrastructure.adapter.out.persistence.repository.CommunicationMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommunicationMessagePersistenceAdapter implements CommunicationMessageRepositoryPort {

    private final CommunicationMessageJpaRepository repository;
    private final CommunicationMessagePersistenceMapper mapper;

    @Override
    public CommunicationMessage save(CommunicationMessage message) {
        CommunicationMessageJpaEntity entity = mapper.toEntity(message);
        CommunicationMessageJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CommunicationMessage> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<CommunicationMessage> findAll(Integer offset, Integer limit) {
        int page = (offset != null && limit != null && limit > 0) ? offset / limit : 0;
        int size = (limit != null && limit > 0) ? limit : 20;
        Page<CommunicationMessageJpaEntity> entityPage = repository.findAll(PageRequest.of(page, size));
        return entityPage.stream().map(mapper::toDomain).toList();
    }
}
