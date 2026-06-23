package com.subscriptionengine.productcatalog.api.controller;

import com.subscriptionengine.productcatalog.api.mapper.ProductSpecificationMapper;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductSpecificationUseCase;
import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductSpecificationApi;
import org.openapitools.model.ProductSpecificationCreate;
import org.openapitools.model.ProductSpecificationUpdate;
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
public class ProductSpecificationApiController implements ProductSpecificationApi {

    private final ManageProductSpecificationUseCase useCase;
    private final ProductSpecificationMapper mapper;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<org.openapitools.model.ProductSpecification> createProductSpecification(ProductSpecificationCreate productSpecificationCreate) {
        ProductSpecification domain = mapper.toDomain(productSpecificationCreate);
        ProductSpecification created = useCase.createProductSpecification(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProductSpecification(String id) {
        useCase.deleteProductSpecification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.ProductSpecification>> listProductSpecification(String fields, Integer offset, Integer limit) {
        Map<String, String> filters = new HashMap<>();
        if (request != null && request.getParameterMap() != null) {
            request.getParameterMap().forEach((key, values) -> {
                if (values != null && values.length > 0) {
                    filters.put(key, values[0]);
                }
            });
        }
        
        List<org.openapitools.model.ProductSpecification> dtos = useCase.listProductSpecifications(offset, limit, filters).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductSpecification> patchProductSpecification(String id, ProductSpecificationUpdate productSpecificationUpdate) {
        ProductSpecification domain = mapper.toDomain(productSpecificationUpdate);
        ProductSpecification updated = useCase.updateProductSpecification(id, domain);
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductSpecification> retrieveProductSpecification(String id, String fields) {
        ProductSpecification found = useCase.getProductSpecificationById(id);
        return new ResponseEntity<>(mapper.toDto(found), HttpStatus.OK);
    }
}

