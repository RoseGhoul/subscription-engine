package com.subscriptionengine.productcatalog.api.controller;

import com.subscriptionengine.productcatalog.api.mapper.ProductOfferingMapper;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductOfferingUseCase;
import com.subscriptionengine.productcatalog.domain.model.ProductOffering;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductOfferingApi;
import org.openapitools.model.ProductOfferingCreate;
import org.openapitools.model.ProductOfferingUpdate;
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
public class ProductOfferingApiController implements ProductOfferingApi {

    private final ManageProductOfferingUseCase useCase;
    private final ProductOfferingMapper mapper;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<org.openapitools.model.ProductOffering> createProductOffering(ProductOfferingCreate productOfferingCreate) {
        ProductOffering domain = mapper.toDomain(productOfferingCreate);
        ProductOffering created = useCase.createProductOffering(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProductOffering(String id) {
        useCase.deleteProductOffering(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.ProductOffering>> listProductOffering(String fields, Integer offset, Integer limit) {
        Map<String, String> filters = new HashMap<>();
        if (request != null && request.getParameterMap() != null) {
            request.getParameterMap().forEach((key, values) -> {
                if (values != null && values.length > 0) {
                    filters.put(key, values[0]);
                }
            });
        }
        
        List<org.openapitools.model.ProductOffering> dtos = useCase.listProductOfferings(offset, limit, filters).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOffering> patchProductOffering(String id, ProductOfferingUpdate productOfferingUpdate) {
        ProductOffering domain = mapper.toDomain(productOfferingUpdate);
        ProductOffering updated = useCase.updateProductOffering(id, domain);
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOffering> retrieveProductOffering(String id, String fields) {
        ProductOffering found = useCase.getProductOfferingById(id);
        return new ResponseEntity<>(mapper.toDto(found), HttpStatus.OK);
    }
}

