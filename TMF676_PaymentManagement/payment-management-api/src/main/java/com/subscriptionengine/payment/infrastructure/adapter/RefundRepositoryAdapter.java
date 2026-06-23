package com.subscriptionengine.payment.infrastructure.adapter;

import com.subscriptionengine.payment.domain.model.Refund;
import com.subscriptionengine.payment.domain.repository.RefundRepository;
import com.subscriptionengine.payment.infrastructure.mapper.EntityMapper;
import com.subscriptionengine.payment.infrastructure.persistence.entity.RefundJpaEntity;
import com.subscriptionengine.payment.infrastructure.persistence.repository.SpringDataRefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RefundRepositoryAdapter implements RefundRepository {

    private final SpringDataRefundRepository springDataRefundRepository;
    private final EntityMapper entityMapper;

    @Override
    public Refund save(Refund refund) {
        Optional<RefundJpaEntity> existingEntityOpt = springDataRefundRepository.findById(refund.getId());
        RefundJpaEntity entityToSave;
        
        if (existingEntityOpt.isPresent()) {
            entityToSave = existingEntityOpt.get();
            entityMapper.updateRefundEntityFromDomain(refund, entityToSave);
        } else {
            entityToSave = entityMapper.toEntity(refund);
        }
        
        RefundJpaEntity savedEntity = springDataRefundRepository.save(entityToSave);
        return entityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Refund> findById(String id) {
        return springDataRefundRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Refund> findAll() {
        return springDataRefundRepository.findAll().stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
