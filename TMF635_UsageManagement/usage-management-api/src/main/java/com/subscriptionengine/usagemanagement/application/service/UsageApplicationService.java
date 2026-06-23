package com.subscriptionengine.usagemanagement.application.service;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import com.subscriptionengine.usagemanagement.domain.validation.UsageValidator;
import com.subscriptionengine.usagemanagement.domain.event.UsageEventPublisher;
import com.subscriptionengine.usagemanagement.infrastructure.persistence.adapter.UsageRepositoryAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsageApplicationService {

    private final UsageRepositoryAdapter repositoryAdapter;
    private final UsageValidator usageValidator;
    private final UsageEventPublisher eventPublisher;

    public UsageApplicationService(UsageRepositoryAdapter repositoryAdapter, UsageValidator usageValidator, UsageEventPublisher eventPublisher) {
        this.repositoryAdapter = repositoryAdapter;
        this.usageValidator = usageValidator;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Usage createUsage(Usage usage) {
        usage.setId(UUID.randomUUID());
        usage.setHref("/usageManagement/v4/usage/" + usage.getId());
        
        if (usage.getUsageDate() == null) {
            usage.setUsageDate(OffsetDateTime.now());
        }
        
        if (usage.getStatus() == null) {
            usage.setStatus("received");
        }

        usageValidator.validate(usage);

        Usage savedUsage = repositoryAdapter.save(usage);
        eventPublisher.publishUsageCreatedEvent(savedUsage);
        return savedUsage;
    }

    @Transactional(readOnly = true)
    public Optional<Usage> getUsage(String id) {
        return repositoryAdapter.findById(id);
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Usage> listUsage(String fields, Integer offset, Integer limit) {
        int pageNumber = (offset != null && offset > 0) ? offset : 0;
        int pageSize = (limit != null && limit > 0) ? limit : 20;
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNumber, pageSize);
        return repositoryAdapter.findAll(pageable);
    }

    @Transactional
    public Usage patchUsage(String id, Usage patch) {
        Usage existing = repositoryAdapter.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usage not found for id: " + id));
        
        if (patch.getDescription() != null) existing.setDescription(patch.getDescription());
        if (patch.getStatus() != null && !patch.getStatus().equals(existing.getStatus())) {
            existing.setStatus(patch.getStatus());
            eventPublisher.publishUsageStateChangeEvent(existing);
        }
        if (patch.getUsageType() != null) existing.setUsageType(patch.getUsageType());
        if (patch.getUsageDate() != null) existing.setUsageDate(patch.getUsageDate());
        if (patch.getUsageCharacteristic() != null && !patch.getUsageCharacteristic().isEmpty()) {
            existing.setUsageCharacteristic(patch.getUsageCharacteristic());
        }
        if (patch.getRatedProductUsage() != null && !patch.getRatedProductUsage().isEmpty()) {
            existing.setRatedProductUsage(patch.getRatedProductUsage());
        }
        if (patch.getRelatedParty() != null && !patch.getRelatedParty().isEmpty()) {
            existing.setRelatedParty(patch.getRelatedParty());
        }
        
        usageValidator.validate(existing);
        return repositoryAdapter.save(existing);
    }

    @Transactional
    public void deleteUsage(String id) {
        repositoryAdapter.deleteById(id);
    }
}
