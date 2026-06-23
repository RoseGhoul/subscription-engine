package com.subscriptionengine.productcatalog.application.service;

import com.subscriptionengine.productcatalog.application.port.in.ManageCategoryUseCase;
import com.subscriptionengine.productcatalog.application.port.out.CategoryRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryApplicationService implements ManageCategoryUseCase {

    private final CategoryRepositoryPort repositoryPort;

    @Override
    @Transactional
    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID().toString());
        return repositoryPort.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new com.subscriptionengine.productcatalog.api.exception.ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> listCategories(Integer offset, Integer limit, Map<String, String> filters) {
        return repositoryPort.findAll(offset, limit, filters);
    }

    @Override
    public Category updateCategory(String id, Category category) {
        Category existing = getCategoryById(id);
        
        if (category.getName() != null) existing.setName(category.getName());
        if (category.getDescription() != null) existing.setDescription(category.getDescription());
        if (category.getIsRoot() != null) existing.setIsRoot(category.getIsRoot());
        if (category.getParentId() != null) existing.setParentId(category.getParentId());
        if (category.getLifecycleStatus() != null) existing.setLifecycleStatus(category.getLifecycleStatus());
        if (category.getVersion() != null) existing.setVersion(category.getVersion());
        if (category.getValidFor() != null) existing.setValidFor(category.getValidFor());

        return repositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void deleteCategory(String id) {
        getCategoryById(id); // verify it exists
        repositoryPort.deleteById(id);
    }
}
