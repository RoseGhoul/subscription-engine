package com.subscriptionengine.productcatalog.api.controller;

import com.subscriptionengine.productcatalog.api.mapper.ProductOfferingPriceMapper;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductOfferingPriceUseCase;
import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductOfferingPriceApi;
import org.openapitools.model.ProductOfferingPriceCreate;
import org.openapitools.model.ProductOfferingPriceUpdate;
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
public class ProductOfferingPriceApiController implements ProductOfferingPriceApi {

    private final ManageProductOfferingPriceUseCase useCase;
    private final ProductOfferingPriceMapper mapper;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<org.openapitools.model.ProductOfferingPrice> createProductOfferingPrice(ProductOfferingPriceCreate productOfferingPriceCreate) {
        ProductOfferingPrice domain = mapper.toDomain(productOfferingPriceCreate);
        ProductOfferingPrice created = useCase.createProductOfferingPrice(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProductOfferingPrice(String id) {
        useCase.deleteProductOfferingPrice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.ProductOfferingPrice>> listProductOfferingPrice(String fields, Integer offset, Integer limit) {
        Map<String, String> filters = new HashMap<>();
        if (request != null && request.getParameterMap() != null) {
            request.getParameterMap().forEach((key, values) -> {
                if (values != null && values.length > 0) {
                    filters.put(key, values[0]);
                }
            });
        }
        
        List<org.openapitools.model.ProductOfferingPrice> dtos = useCase.listProductOfferingPrices(offset, limit, filters).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOfferingPrice> patchProductOfferingPrice(String id, ProductOfferingPriceUpdate productOfferingPriceUpdate) {
        ProductOfferingPrice domain = mapper.toDomain(productOfferingPriceUpdate);
        ProductOfferingPrice updated = useCase.updateProductOfferingPrice(id, domain);
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOfferingPrice> retrieveProductOfferingPrice(String id, String fields) {
        ProductOfferingPrice found = useCase.getProductOfferingPriceById(id);
        return new ResponseEntity<>(mapper.toDto(found), HttpStatus.OK);
    }
}

