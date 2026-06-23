package com.subscriptionengine.productcatalog.application.port.out;

import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;

import java.util.List;
import java.util.Optional;

import java.util.Map;

public interface ProductSpecificationRepositoryPort {
    ProductSpecification save(ProductSpecification productSpecification);
    Optional<ProductSpecification> findById(String id);
    List<ProductSpecification> findAll(Integer offset, Integer limit, Map<String, String> filters);
    void deleteById(String id);
}
