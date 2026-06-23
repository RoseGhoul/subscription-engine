package com.subscriptionengine.productorder.api.controller;

import com.subscriptionengine.productorder.api.mapper.ProductOrderApiMapper;
import com.subscriptionengine.productorder.application.port.in.ManageProductOrderUseCase;
import com.subscriptionengine.productorder.domain.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductOrderApi;
import org.openapitools.model.ProductOrderCreate;
import org.openapitools.model.ProductOrderUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/tmf-api/productOrderingManagement/v4")
@org.springframework.validation.annotation.Validated
@RequiredArgsConstructor
public class ProductOrderApiController implements ProductOrderApi {

    private final ManageProductOrderUseCase useCase;
    private final ProductOrderApiMapper mapper;
    private final jakarta.servlet.http.HttpServletRequest request;

    @Override
    public ResponseEntity<org.openapitools.model.ProductOrder> createProductOrder(ProductOrderCreate productOrderCreate) {
        ProductOrder domain = mapper.toDomain(productOrderCreate);
        ProductOrder saved = useCase.createProductOrder(domain);
        
        saved.setHref(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUriString());
        ProductOrder updatedWithHref = useCase.updateProductOrder(saved.getId(), saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(updatedWithHref));
    }

    @Override
    public ResponseEntity<Void> deleteProductOrder(String id) {
        useCase.deleteProductOrder(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.ProductOrder>> listProductOrder(String fields, Integer offset, Integer limit) {
        java.util.Map<String, String> filters = new java.util.HashMap<>();
        request.getParameterMap().forEach((k, v) -> {
            if (v != null && v.length > 0) {
                filters.put(k, v[0]);
            }
        });
        
        List<org.openapitools.model.ProductOrder> dtos = useCase.listProductOrders(filters, offset, limit).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOrder> patchProductOrder(String id, ProductOrderUpdate productOrderUpdate) {
        ProductOrder domain = mapper.toDomain(productOrderUpdate);
        ProductOrder updated = useCase.updateProductOrder(UUID.fromString(id), domain);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Override
    public ResponseEntity<org.openapitools.model.ProductOrder> retrieveProductOrder(String id, String fields) {
        ProductOrder domain = useCase.getProductOrderById(UUID.fromString(id));
        return ResponseEntity.ok(mapper.toDto(domain));
    }
}
