package com.subscriptionengine.productcatalog.application.service;

import com.subscriptionengine.productcatalog.api.exception.ResourceNotFoundException;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductOfferingUseCase;
import com.subscriptionengine.productcatalog.application.port.out.ProductOfferingRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductOffering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductOfferingApplicationService implements ManageProductOfferingUseCase {

    private final ProductOfferingRepositoryPort repositoryPort;

    @Override
    @Transactional
    public ProductOffering createProductOffering(ProductOffering productOffering) {
        productOffering.setId(UUID.randomUUID().toString());
        return repositoryPort.save(productOffering);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductOffering getProductOfferingById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOffering not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductOffering> listProductOfferings(Integer offset, Integer limit, Map<String, String> filters) {
        return repositoryPort.findAll(offset, limit, filters);
    }

    @Override
    public ProductOffering updateProductOffering(String id, ProductOffering productOffering) {
        ProductOffering existing = getProductOfferingById(id);
        
        if (productOffering.getName() != null) existing.setName(productOffering.getName());
        if (productOffering.getDescription() != null) existing.setDescription(productOffering.getDescription());
        if (productOffering.getIsBundle() != null) existing.setIsBundle(productOffering.getIsBundle());
        if (productOffering.getIsSellable() != null) existing.setIsSellable(productOffering.getIsSellable());
        if (productOffering.getStatusReason() != null) existing.setStatusReason(productOffering.getStatusReason());
        if (productOffering.getLifecycleStatus() != null) existing.setLifecycleStatus(productOffering.getLifecycleStatus());
        if (productOffering.getVersion() != null) existing.setVersion(productOffering.getVersion());
        if (productOffering.getValidFor() != null) existing.setValidFor(productOffering.getValidFor());
        if (productOffering.getCategory() != null) existing.setCategory(productOffering.getCategory());
        if (productOffering.getProductSpecification() != null) existing.setProductSpecification(productOffering.getProductSpecification());
        if (productOffering.getProductOfferingPrice() != null) existing.setProductOfferingPrice(productOffering.getProductOfferingPrice());
        if (productOffering.getBundledProductOffering() != null) existing.setBundledProductOffering(productOffering.getBundledProductOffering());

        return repositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void deleteProductOffering(String id) {
        getProductOfferingById(id); // verify it exists
        repositoryPort.deleteById(id);
    }
}
