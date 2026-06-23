package com.subscriptionengine.usagemanagement.infrastructure.persistence.adapter;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import com.subscriptionengine.usagemanagement.infrastructure.persistence.entity.UsageJpaEntity;
import com.subscriptionengine.usagemanagement.infrastructure.persistence.mapper.UsagePersistenceMapper;
import com.subscriptionengine.usagemanagement.infrastructure.persistence.repository.UsageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsageRepositoryAdapter {

    private final UsageRepository repository;
    private final UsagePersistenceMapper mapper;

    public UsageRepositoryAdapter(UsageRepository repository, UsagePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Usage save(Usage domain) {
        UsageJpaEntity entity;
        if (domain.getId() != null && repository.existsById(domain.getId())) {
            entity = repository.findById(domain.getId()).orElseThrow();
            mapper.updateEntityFromDomain(domain, entity);
        } else {
            entity = mapper.toEntity(domain);
        }
        return mapper.toDomain(repository.save(entity));
    }

    public Optional<Usage> findById(String id) {
        return repository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    public java.util.List<Usage> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    public org.springframework.data.domain.Page<Usage> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }
    
    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
