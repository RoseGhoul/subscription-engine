package com.subscriptionengine.productcatalog.application.service;

import com.subscriptionengine.productcatalog.api.exception.ResourceNotFoundException;
import com.subscriptionengine.productcatalog.application.port.in.ManageProductOfferingPriceUseCase;
import com.subscriptionengine.productcatalog.application.port.out.ProductOfferingPriceRepositoryPort;
import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductOfferingPriceApplicationService implements ManageProductOfferingPriceUseCase {

    private final ProductOfferingPriceRepositoryPort repositoryPort;

    @Override
    @Transactional
    public ProductOfferingPrice createProductOfferingPrice(ProductOfferingPrice productOfferingPrice) {
        productOfferingPrice.setId(UUID.randomUUID().toString());
        return repositoryPort.save(productOfferingPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductOfferingPrice getProductOfferingPriceById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOfferingPrice not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductOfferingPrice> listProductOfferingPrices(Integer offset, Integer limit, Map<String, String> filters) {
        return repositoryPort.findAll(offset, limit, filters);
    }

    @Override
    public ProductOfferingPrice updateProductOfferingPrice(String id, ProductOfferingPrice productOfferingPrice) {
        ProductOfferingPrice existing = getProductOfferingPriceById(id);
        
        if (productOfferingPrice.getName() != null) existing.setName(productOfferingPrice.getName());
        if (productOfferingPrice.getDescription() != null) existing.setDescription(productOfferingPrice.getDescription());
        if (productOfferingPrice.getIsBundle() != null) existing.setIsBundle(productOfferingPrice.getIsBundle());
        if (productOfferingPrice.getPercentage() != null) existing.setPercentage(productOfferingPrice.getPercentage());
        if (productOfferingPrice.getPriceType() != null) existing.setPriceType(productOfferingPrice.getPriceType());
        if (productOfferingPrice.getRecurringChargePeriodLength() != null) existing.setRecurringChargePeriodLength(productOfferingPrice.getRecurringChargePeriodLength());
        if (productOfferingPrice.getRecurringChargePeriodType() != null) existing.setRecurringChargePeriodType(productOfferingPrice.getRecurringChargePeriodType());
        if (productOfferingPrice.getPrice() != null) existing.setPrice(productOfferingPrice.getPrice());
        if (productOfferingPrice.getUnitOfMeasure() != null) existing.setUnitOfMeasure(productOfferingPrice.getUnitOfMeasure());
        if (productOfferingPrice.getLifecycleStatus() != null) existing.setLifecycleStatus(productOfferingPrice.getLifecycleStatus());
        if (productOfferingPrice.getVersion() != null) existing.setVersion(productOfferingPrice.getVersion());
        if (productOfferingPrice.getValidFor() != null) existing.setValidFor(productOfferingPrice.getValidFor());

        return repositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void deleteProductOfferingPrice(String id) {
        getProductOfferingPriceById(id); // verify it exists
        repositoryPort.deleteById(id);
    }
}
