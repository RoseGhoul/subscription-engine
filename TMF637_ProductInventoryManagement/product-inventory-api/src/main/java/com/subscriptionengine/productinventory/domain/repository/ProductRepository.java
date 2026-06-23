package com.subscriptionengine.productinventory.domain.repository;

import com.subscriptionengine.productinventory.domain.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    Optional<Product> findByProductSerialNumber(String serialNumber);
    org.springframework.data.domain.Page<Product> listProduct(String name, String status, Boolean isBundle, org.springframework.data.domain.Pageable pageable);
    void deleteById(String id);
}
