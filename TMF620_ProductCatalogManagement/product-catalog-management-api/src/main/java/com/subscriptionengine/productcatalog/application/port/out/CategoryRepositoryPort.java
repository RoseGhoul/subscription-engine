package com.subscriptionengine.productcatalog.application.port.out;

import com.subscriptionengine.productcatalog.domain.model.Category;

import java.util.List;
import java.util.Optional;

import java.util.Map;

public interface CategoryRepositoryPort {
    Category save(Category category);
    Optional<Category> findById(String id);
    List<Category> findAll(Integer offset, Integer limit, Map<String, String> filters);
    void deleteById(String id);
}
