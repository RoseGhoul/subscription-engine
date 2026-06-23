package com.subscriptionengine.payment.infrastructure.adapter;

import com.subscriptionengine.payment.domain.model.Payment;
import com.subscriptionengine.payment.domain.repository.PaymentRepository;
import com.subscriptionengine.payment.infrastructure.mapper.EntityMapper;
import com.subscriptionengine.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import com.subscriptionengine.payment.infrastructure.persistence.repository.SpringDataPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final SpringDataPaymentRepository springDataPaymentRepository;
    private final EntityMapper entityMapper;

    @Override
    public Payment save(Payment payment) {
        Optional<PaymentJpaEntity> existingEntityOpt = springDataPaymentRepository.findById(payment.getId());
        PaymentJpaEntity entityToSave;
        
        if (existingEntityOpt.isPresent()) {
            entityToSave = existingEntityOpt.get();
            entityMapper.updateEntityFromDomain(payment, entityToSave);
        } else {
            entityToSave = entityMapper.toEntity(payment);
        }
        
        PaymentJpaEntity savedEntity = springDataPaymentRepository.save(entityToSave);
        return entityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return springDataPaymentRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Payment> findAll(int page, int size) {
        org.springframework.data.domain.PageRequest pageRequest = org.springframework.data.domain.PageRequest.of(page, size);
        return springDataPaymentRepository.findAll(pageRequest)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
