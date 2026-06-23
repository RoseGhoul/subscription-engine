package com.subscriptionengine.productorder.application.port.in;

import com.subscriptionengine.productorder.domain.model.ProductOrder;

import java.util.List;
import java.util.UUID;

public interface ManageProductOrderUseCase {

    ProductOrder createProductOrder(ProductOrder productOrder);

    ProductOrder getProductOrderById(UUID id);

    List<ProductOrder> listProductOrders(java.util.Map<String, String> filters, Integer offset, Integer limit);

    ProductOrder updateProductOrder(UUID id, ProductOrder productOrder);

    void deleteProductOrder(UUID id);
}
