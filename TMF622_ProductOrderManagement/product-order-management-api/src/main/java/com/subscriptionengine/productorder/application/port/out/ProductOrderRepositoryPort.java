package com.subscriptionengine.productorder.application.port.out;

import com.subscriptionengine.productorder.domain.model.ProductOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductOrderRepositoryPort {

    ProductOrder save(ProductOrder productOrder);

    Optional<ProductOrder> findById(UUID id);

    List<ProductOrder> findAll(java.util.Map<String, String> filters, Integer offset, Integer limit);

    void deleteById(UUID id);
}
