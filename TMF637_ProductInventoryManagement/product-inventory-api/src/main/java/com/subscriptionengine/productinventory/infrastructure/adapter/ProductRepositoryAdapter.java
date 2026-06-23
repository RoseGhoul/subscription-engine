package com.subscriptionengine.productinventory.infrastructure.adapter;

import com.subscriptionengine.productinventory.domain.model.Product;
import com.subscriptionengine.productinventory.domain.repository.ProductRepository;
import com.subscriptionengine.productinventory.infrastructure.entity.ProductJpaEntity;
import com.subscriptionengine.productinventory.infrastructure.mapper.EntityMapper;
import com.subscriptionengine.productinventory.infrastructure.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final EntityMapper mapper;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository, EntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        if (product.getId() != null) {
            java.util.Optional<ProductJpaEntity> existingOpt = jpaRepository.findById(product.getId());
            if (existingOpt.isPresent()) {
                ProductJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(product, existingEntity);
                return mapper.toDomain(jpaRepository.save(existingEntity));
            }
        }
        ProductJpaEntity entity = mapper.toEntity(product);
        ProductJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Product> findByProductSerialNumber(String serialNumber) {
        return jpaRepository.findByProductSerialNumber(serialNumber)
                .map(mapper::toDomain);
    }

    @Override
    public org.springframework.data.domain.Page<Product> listProduct(String name, String status, Boolean isBundle, org.springframework.data.domain.Pageable pageable) {
        return jpaRepository.findAll(
                com.subscriptionengine.productinventory.infrastructure.repository.specification.ProductSpecification.filterBy(name, status, isBundle),
                pageable
        ).map(mapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.findById(id).ifPresent(entity -> {
            // Initialize and clear collections to force Hibernate to issue child DELETEs before parent DELETE
            if (entity.getProductCharacteristic() != null) entity.getProductCharacteristic().clear();
            if (entity.getProductPrice() != null) entity.getProductPrice().clear();
            if (entity.getProductRelationship() != null) entity.getProductRelationship().clear();
            if (entity.getPlace() != null) entity.getPlace().clear();
            if (entity.getRelatedParty() != null) entity.getRelatedParty().clear();
            jpaRepository.delete(entity);
        });
    }
}
