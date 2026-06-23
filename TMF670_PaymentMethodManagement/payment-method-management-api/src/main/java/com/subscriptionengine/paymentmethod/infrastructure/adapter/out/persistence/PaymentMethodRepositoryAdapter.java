package com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;
import com.subscriptionengine.paymentmethod.domain.port.out.PaymentMethodRepositoryPort;
import com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence.entity.PaymentMethodJpaEntity;
import com.subscriptionengine.paymentmethod.infrastructure.adapter.out.persistence.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentMethodRepositoryAdapter implements PaymentMethodRepositoryPort {

    private final PaymentMethodRepository repository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        PaymentMethodJpaEntity entity = toEntity(paymentMethod);
        PaymentMethodJpaEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<PaymentMethod> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    private PaymentMethodJpaEntity toEntity(PaymentMethod domain) {
        PaymentMethodJpaEntity entity = new PaymentMethodJpaEntity();
        BeanUtils.copyProperties(domain, entity);
        return entity;
    }

    private PaymentMethod domain;
    private PaymentMethod toDomain(PaymentMethodJpaEntity entity) {
        PaymentMethod domain = new PaymentMethod();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
