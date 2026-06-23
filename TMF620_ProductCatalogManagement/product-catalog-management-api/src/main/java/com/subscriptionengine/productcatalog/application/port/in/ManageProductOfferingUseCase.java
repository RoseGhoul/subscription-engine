package com.subscriptionengine.productcatalog.application.port.in;

import com.subscriptionengine.productcatalog.domain.model.ProductOffering;

import java.util.List;
import java.util.Map;

public interface ManageProductOfferingUseCase {
    ProductOffering createProductOffering(ProductOffering productOffering);
    ProductOffering getProductOfferingById(String id);
    List<ProductOffering> listProductOfferings(Integer offset, Integer limit, Map<String, String> filters);
    ProductOffering updateProductOffering(String id, ProductOffering productOffering);
    void deleteProductOffering(String id);
}
