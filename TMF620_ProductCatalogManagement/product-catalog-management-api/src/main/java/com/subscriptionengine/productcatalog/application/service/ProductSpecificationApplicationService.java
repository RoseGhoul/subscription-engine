package com.subscriptionengine.productcatalog.application.service;

import com.subscriptionengine.productcatalog.api.exception.ResourceNotFoundException;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductSpecificationUseCase;
import com.subscriptionengine.productcatalog.application.port.out.ProductSpecificationRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductSpecificationApplicationService implements ManageProductSpecificationUseCase {

    private final ProductSpecificationRepositoryPort repositoryPort;

    @Override
    @Transactional
    public ProductSpecification createProductSpecification(ProductSpecification productSpecification) {
        productSpecification.setId(UUID.randomUUID().toString());
        return repositoryPort.save(productSpecification);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductSpecification getProductSpecificationById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductSpecification not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSpecification> listProductSpecifications(Integer offset, Integer limit, Map<String, String> filters) {
        return repositoryPort.findAll(offset, limit, filters);
    }

    @Override
    public ProductSpecification updateProductSpecification(String id, ProductSpecification productSpecification) {
        ProductSpecification existing = getProductSpecificationById(id);
        
        if (productSpecification.getName() != null) existing.setName(productSpecification.getName());
        if (productSpecification.getBrand() != null) existing.setBrand(productSpecification.getBrand());
        if (productSpecification.getDescription() != null) existing.setDescription(productSpecification.getDescription());
        if (productSpecification.getProductNumber() != null) existing.setProductNumber(productSpecification.getProductNumber());
        if (productSpecification.getIsBundle() != null) existing.setIsBundle(productSpecification.getIsBundle());
        if (productSpecification.getLifecycleStatus() != null) existing.setLifecycleStatus(productSpecification.getLifecycleStatus());
        if (productSpecification.getVersion() != null) existing.setVersion(productSpecification.getVersion());
        if (productSpecification.getValidFor() != null) existing.setValidFor(productSpecification.getValidFor());

        return repositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void deleteProductSpecification(String id) {
        getProductSpecificationById(id); // verify it exists
        repositoryPort.deleteById(id);
    }
}
