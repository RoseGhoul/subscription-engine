package com.subscriptionengine.productcatalog.infrastructure.persistence.adapter;

import com.subscriptionengine.productcatalog.application.port.out.ProductOfferingPriceRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingPriceJpaEntity;
import com.subscriptionengine.productcatalog.infrastructure.persistence.mapper.ProductOfferingPricePersistenceMapper;
import com.subscriptionengine.productcatalog.infrastructure.persistence.repository.ProductOfferingPriceJpaRepository;
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
public class ProductOfferingPriceRepositoryAdapter implements ProductOfferingPriceRepositoryPort {

    private final ProductOfferingPriceJpaRepository repository;
    private final ProductOfferingPricePersistenceMapper mapper;

    @Override
    public ProductOfferingPrice save(ProductOfferingPrice productOfferingPrice) {
        if (productOfferingPrice.getId() != null) {
            java.util.Optional<ProductOfferingPriceJpaEntity> existingOpt = repository.findById(productOfferingPrice.getId());
            if (existingOpt.isPresent()) {
                ProductOfferingPriceJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(productOfferingPrice, existingEntity);
                return mapper.toDomain(repository.save(existingEntity));
            }
        }
        ProductOfferingPriceJpaEntity entity = mapper.toEntity(productOfferingPrice);
        ProductOfferingPriceJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ProductOfferingPrice> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProductOfferingPrice> findAll(Integer offset, Integer limit, Map<String, String> filters) {
        int size = (limit != null && limit > 0) ? limit : 20;
        int page = (offset != null && offset > 0) ? offset / size : 0;
        Pageable pageable = PageRequest.of(page, size);
        
        Specification<ProductOfferingPriceJpaEntity> spec = new DynamicSpecificationBuilder<ProductOfferingPriceJpaEntity>().buildSpecification(filters);

        return repository.findAll(spec, pageable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
