package com.subscriptionengine.productcatalog.infrastructure.persistence.adapter;

import com.subscriptionengine.productcatalog.application.port.out.CategoryRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.Category;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.CategoryJpaEntity;
import com.subscriptionengine.productcatalog.infrastructure.persistence.mapper.CategoryPersistenceMapper;
import com.subscriptionengine.productcatalog.infrastructure.persistence.repository.CategoryJpaRepository;
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
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository repository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public Category save(Category category) {
        if (category.getId() != null) {
            java.util.Optional<CategoryJpaEntity> existingOpt = repository.findById(category.getId());
            if (existingOpt.isPresent()) {
                CategoryJpaEntity existingEntity = existingOpt.get();
                mapper.updateEntityFromDomain(category, existingEntity);
                return mapper.toDomain(repository.save(existingEntity));
            }
        }
        CategoryJpaEntity entity = mapper.toEntity(category);
        CategoryJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll(Integer offset, Integer limit, Map<String, String> filters) {
        int size = (limit != null && limit > 0) ? limit : 20;
        int page = (offset != null && offset > 0) ? offset / size : 0;
        Pageable pageable = PageRequest.of(page, size);
        
        Specification<CategoryJpaEntity> spec = new DynamicSpecificationBuilder<CategoryJpaEntity>().buildSpecification(filters);

        return repository.findAll(spec, pageable).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
