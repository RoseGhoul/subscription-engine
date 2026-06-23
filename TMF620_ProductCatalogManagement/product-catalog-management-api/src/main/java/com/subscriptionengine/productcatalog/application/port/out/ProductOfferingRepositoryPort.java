package com.subscriptionengine.productcatalog.application.port.out;

import com.subscriptionengine.productcatalog.domain.model.ProductOffering;

import java.util.List;
import java.util.Optional;

import java.util.Map;

public interface ProductOfferingRepositoryPort {
    ProductOffering save(ProductOffering productOffering);
    Optional<ProductOffering> findById(String id);
    List<ProductOffering> findAll(Integer offset, Integer limit, Map<String, String> filters);
    void deleteById(String id);
}
