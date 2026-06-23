package com.subscriptionengine.productcatalog.api.controller;

import com.subscriptionengine.productcatalog.api.mapper.CategoryMapper;
import com.subscriptionengine.productcatalog.application.port.in.ManageCategoryUseCase;
import com.subscriptionengine.productcatalog.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CategoryApi;
import org.openapitools.model.CategoryCreate;
import org.openapitools.model.CategoryUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/tmf-api/productCatalogManagement/v4")
@RequiredArgsConstructor
public class CategoryApiController implements CategoryApi {

    private final ManageCategoryUseCase useCase;
    private final CategoryMapper mapper;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<org.openapitools.model.Category> createCategory(CategoryCreate categoryCreate) {
        Category domain = mapper.toDomain(categoryCreate);
        Category created = useCase.createCategory(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(String id) {
        useCase.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Category>> listCategory(String fields, Integer offset, Integer limit) {
        Map<String, String> filters = new HashMap<>();
        if (request != null && request.getParameterMap() != null) {
            request.getParameterMap().forEach((key, values) -> {
                if (values != null && values.length > 0) {
                    filters.put(key, values[0]);
                }
            });
        }
        
        List<org.openapitools.model.Category> dtos = useCase.listCategories(offset, limit, filters).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Category> patchCategory(String id, CategoryUpdate categoryUpdate) {
        Category domain = mapper.toDomain(categoryUpdate);
        Category updated = useCase.updateCategory(id, domain);
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Category> retrieveCategory(String id, String fields) {
        Category found = useCase.getCategoryById(id);
        return new ResponseEntity<>(mapper.toDto(found), HttpStatus.OK);
    }
}

