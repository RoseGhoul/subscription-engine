package com.subscriptionengine.productinventory.api.controller;

import com.subscriptionengine.productinventory.api.mapper.ProductApiMapper;
import com.subscriptionengine.productinventory.application.service.ProductApplicationService;
import com.subscriptionengine.productinventory.domain.model.Product;
import org.openapitools.api.ProductApi;
import org.openapitools.model.ProductCreate;
import org.openapitools.model.ProductUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tmf-api/productInventoryManagement/v4")
public class ProductApiController implements ProductApi {

    private final ProductApplicationService service;
    private final ProductApiMapper mapper;

    public ProductApiController(ProductApplicationService service, ProductApiMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<org.openapitools.model.Product> createProduct(ProductCreate productCreate) {
        // Since we are not fully mapping ProductCreate yet, we will do a basic map
        // Normally we'd map ProductCreate -> Product domain object
        Product domain = new Product();
        domain.setName(productCreate.getName());
        domain.setDescription(productCreate.getDescription());
        domain.setProductSerialNumber(productCreate.getProductSerialNumber());
        domain.setStartDate(productCreate.getStartDate());
        domain.setTerminationDate(productCreate.getTerminationDate());
        domain.setIsBundle(productCreate.getIsBundle());
        domain.setIsCustomerVisible(productCreate.getIsCustomerVisible());
        if (productCreate.getStatus() != null) {
            domain.setStatus(productCreate.getStatus().getValue());
        }
        
        if (productCreate.getProductOffering() != null) {
            domain.setProductOffering(new Product.ProductOfferingRef(
                productCreate.getProductOffering().getId(),
                productCreate.getProductOffering().getHref(),
                productCreate.getProductOffering().getName()
            ));
        }

        if (productCreate.getProductSpecification() != null) {
            domain.setProductSpecification(new Product.ProductSpecificationRef(
                productCreate.getProductSpecification().getId(),
                productCreate.getProductSpecification().getHref(),
                productCreate.getProductSpecification().getName(),
                productCreate.getProductSpecification().getVersion()
            ));
        }
        
        domain.setProductCharacteristic(mapper.mapCharacteristics(productCreate.getProductCharacteristic()));
        domain.setProductPrice(mapper.mapPrices(productCreate.getProductPrice()));
        domain.setProductRelationship(mapper.mapRelationships(productCreate.getProductRelationship()));
        domain.setPlace(mapper.mapPlaces(productCreate.getPlace()));
        domain.setRelatedParty(mapper.mapRelatedParties(productCreate.getRelatedParty()));
        
        Product saved = service.createProduct(domain);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Product> retrieveProduct(String id, String fields) {
        return service.getProduct(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<org.openapitools.model.Product> patchProduct(String id, ProductUpdate productUpdate) {
        Product patch = new Product();
        patch.setName(productUpdate.getName());
        patch.setDescription(productUpdate.getDescription());
        patch.setStartDate(productUpdate.getStartDate());
        patch.setTerminationDate(productUpdate.getTerminationDate());
        patch.setIsBundle(productUpdate.getIsBundle());
        patch.setIsCustomerVisible(productUpdate.getIsCustomerVisible());
        if (productUpdate.getStatus() != null) {
            patch.setStatus(productUpdate.getStatus().getValue());
        }

        patch.setProductCharacteristic(mapper.mapCharacteristics(productUpdate.getProductCharacteristic()));
        patch.setProductPrice(mapper.mapPrices(productUpdate.getProductPrice()));
        patch.setProductRelationship(mapper.mapRelationships(productUpdate.getProductRelationship()));
        patch.setPlace(mapper.mapPlaces(productUpdate.getPlace()));
        patch.setRelatedParty(mapper.mapRelatedParties(productUpdate.getRelatedParty()));

        try {
            Product updated = service.patchProduct(id, patch);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @org.springframework.beans.factory.annotation.Autowired
    private jakarta.servlet.http.HttpServletRequest request;

    @Override
    public ResponseEntity<java.util.List<org.openapitools.model.Product>> listProduct(String fields, Integer offset, Integer limit) {
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String isBundleParam = request.getParameter("isBundle");
        Boolean isBundle = isBundleParam != null ? Boolean.parseBoolean(isBundleParam) : null;

        int pageNumber = offset != null ? offset : 0;
        int pageSize = limit != null ? limit : 20;
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNumber, pageSize);

        org.springframework.data.domain.Page<Product> page = service.listProduct(name, status, isBundle, pageable);
        
        java.util.List<org.openapitools.model.Product> products = page.getContent().stream()
                .map(mapper::toDto)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Result-Count", String.valueOf(products.size()))
                .body(products);
    }
}
