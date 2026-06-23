package com.subscriptionengine.productorder.infrastructure.persistence.adapter;

import com.subscriptionengine.productorder.application.port.out.ProductOrderRepositoryPort;
import com.subscriptionengine.productorder.domain.model.ProductOrder;
import com.subscriptionengine.productorder.infrastructure.persistence.entity.ProductOrderJpaEntity;
import com.subscriptionengine.productorder.infrastructure.persistence.mapper.ProductOrderPersistenceMapper;
import com.subscriptionengine.productorder.infrastructure.persistence.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductOrderRepositoryAdapter implements ProductOrderRepositoryPort {

    private final ProductOrderRepository repository;
    private final ProductOrderPersistenceMapper mapper;

    @Override
    public ProductOrder save(ProductOrder productOrder) {
        if (productOrder.getId() != null) {
            Optional<ProductOrderJpaEntity> existingOpt = repository.findById(productOrder.getId());
            if (existingOpt.isPresent()) {
                ProductOrderJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(productOrder, existingEntity);
                return mapper.toDomain(repository.save(existingEntity));
            }
        }
        ProductOrderJpaEntity entity = mapper.toEntity(productOrder);
        ProductOrderJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ProductOrder> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProductOrder> findAll(java.util.Map<String, String> filters, Integer offset, Integer limit) {
        int size = (limit != null && limit > 0) ? limit : 20;
        int page = (offset != null && offset > 0) ? offset / size : 0;
        Pageable pageable = PageRequest.of(page, size);

        org.springframework.data.jpa.domain.Specification<ProductOrderJpaEntity> spec = 
                com.subscriptionengine.productorder.infrastructure.persistence.repository.ProductOrderSpecification.withFilters(filters);

        return repository.findAll(spec, pageable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
