package com.subscriptionengine.productcatalog.application.port.in;

import com.subscriptionengine.productcatalog.domain.model.Category;

import java.util.List;
import java.util.Map;

public interface ManageCategoryUseCase {
    Category createCategory(Category category);
    Category getCategoryById(String id);
    List<Category> listCategories(Integer offset, Integer limit, Map<String, String> filters);
    Category updateCategory(String id, Category category);
    void deleteCategory(String id);
}
