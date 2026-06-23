package com.subscriptionengine.productcatalog.application.port.in;

import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;

import java.util.List;
import java.util.Map;

public interface ManageProductOfferingPriceUseCase {
    ProductOfferingPrice createProductOfferingPrice(ProductOfferingPrice productOfferingPrice);
    ProductOfferingPrice getProductOfferingPriceById(String id);
    List<ProductOfferingPrice> listProductOfferingPrices(Integer offset, Integer limit, Map<String, String> filters);
    ProductOfferingPrice updateProductOfferingPrice(String id, ProductOfferingPrice productOfferingPrice);
    void deleteProductOfferingPrice(String id);
}
