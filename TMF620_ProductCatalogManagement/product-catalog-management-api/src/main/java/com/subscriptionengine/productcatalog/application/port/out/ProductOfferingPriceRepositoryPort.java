package com.subscriptionengine.productcatalog.application.port.out;

import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;

import java.util.List;
import java.util.Optional;

import java.util.Map;

public interface ProductOfferingPriceRepositoryPort {
    ProductOfferingPrice save(ProductOfferingPrice productOfferingPrice);
    Optional<ProductOfferingPrice> findById(String id);
    List<ProductOfferingPrice> findAll(Integer offset, Integer limit, Map<String, String> filters);
    void deleteById(String id);
}
