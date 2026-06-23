package com.subscriptionengine.productcatalog.application.port.in;

import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;

import java.util.List;
import java.util.Map;

public interface ManageProductSpecificationUseCase {
    ProductSpecification createProductSpecification(ProductSpecification productSpecification);
    ProductSpecification getProductSpecificationById(String id);
    List<ProductSpecification> listProductSpecifications(Integer offset, Integer limit, Map<String, String> filters);
    ProductSpecification updateProductSpecification(String id, ProductSpecification productSpecification);
    void deleteProductSpecification(String id);
}
