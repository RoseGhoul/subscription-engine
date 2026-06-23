package com.subscriptionengine.productcatalog.infrastructure.persistence.adapter;

import com.subscriptionengine.productcatalog.application.port.out.ProductOfferingRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductOffering;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingJpaEntity;
import com.subscriptionengine.productcatalog.infrastructure.persistence.mapper.ProductOfferingPersistenceMapper;
import com.subscriptionengine.productcatalog.infrastructure.persistence.repository.ProductOfferingJpaRepository;
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
public class ProductOfferingRepositoryAdapter implements ProductOfferingRepositoryPort {

    private final ProductOfferingJpaRepository repository;
    private final ProductOfferingPersistenceMapper mapper;

    @Override
    public ProductOffering save(ProductOffering productOffering) {
        if (productOffering.getId() != null) {
            java.util.Optional<ProductOfferingJpaEntity> existingOpt = repository.findById(productOffering.getId());
            if (existingOpt.isPresent()) {
                ProductOfferingJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(productOffering, existingEntity);
                return mapper.toDomain(repository.save(existingEntity));
            }
        }
        ProductOfferingJpaEntity entity = mapper.toEntity(productOffering);
        ProductOfferingJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ProductOffering> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProductOffering> findAll(Integer offset, Integer limit, Map<String, String> filters) {
        int size = (limit != null && limit > 0) ? limit : 20;
        int page = (offset != null && offset > 0) ? offset / size : 0;
        Pageable pageable = PageRequest.of(page, size);
        
        Specification<ProductOfferingJpaEntity> spec = new DynamicSpecificationBuilder<ProductOfferingJpaEntity>().buildSpecification(filters);

        return repository.findAll(spec, pageable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
