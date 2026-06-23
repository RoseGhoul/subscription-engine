package com.subscriptionengine.productcatalog.infrastructure.persistence.adapter;

import com.subscriptionengine.productcatalog.application.port.out.ProductSpecificationRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductSpecificationJpaEntity;
import com.subscriptionengine.productcatalog.infrastructure.persistence.mapper.ProductSpecificationPersistenceMapper;
import com.subscriptionengine.productcatalog.infrastructure.persistence.repository.ProductSpecificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.subscriptionengine.productcatalog.infrastructure.persistence.specification.DynamicSpecificationBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductSpecificationRepositoryAdapter implements ProductSpecificationRepositoryPort {

    private final ProductSpecificationJpaRepository repository;
    private final ProductSpecificationPersistenceMapper mapper;

    @Override
    public ProductSpecification save(ProductSpecification productSpecification) {
        if (productSpecification.getId() != null) {
            java.util.Optional<ProductSpecificationJpaEntity> existingOpt = repository.findById(productSpecification.getId());
            if (existingOpt.isPresent()) {
                ProductSpecificationJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(productSpecification, existingEntity);
                return mapper.toDomain(repository.save(existingEntity));
            }
        }
        ProductSpecificationJpaEntity entity = mapper.toEntity(productSpecification);
        ProductSpecificationJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ProductSpecification> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProductSpecification> findAll(Integer offset, Integer limit, Map<String, String> filters) {
        int size = (limit != null && limit > 0) ? limit : 20;
        int page = (offset != null && offset > 0) ? offset / size : 0;
        Pageable pageable = PageRequest.of(page, size);
        
        Specification<ProductSpecificationJpaEntity> spec = new DynamicSpecificationBuilder<ProductSpecificationJpaEntity>().buildSpecification(filters);

        return repository.findAll(spec, pageable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
