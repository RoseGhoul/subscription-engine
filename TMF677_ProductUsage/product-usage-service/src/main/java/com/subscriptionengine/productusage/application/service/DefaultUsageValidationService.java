package com.subscriptionengine.productusage.application.service;

import com.subscriptionengine.productusage.domain.exception.ActiveSubscriptionValidationException;
import com.subscriptionengine.productusage.domain.exception.PlanLimitExceededException;
import com.subscriptionengine.productusage.domain.exception.UsageValidationException;
import com.subscriptionengine.productusage.domain.model.UsageConsumption;
import com.subscriptionengine.productusage.domain.port.in.UsageValidationService;
import org.springframework.stereotype.Service;

@Service
public class DefaultUsageValidationService implements UsageValidationService {

    @Override
    public void validateUsage(UsageConsumption usageConsumption) {
        if (usageConsumption.getProduct() == null || usageConsumption.getProduct().isEmpty()) {
            throw new UsageValidationException("Usage must be tied to a ProductRef.");
        }

        // 1. Mock Active Subscription Validation
        // In the future, this will call TMF637 Product Inventory to check if the ProductRef is ACTIVE
        String productId = usageConsumption.getProduct().get(0).getId();
        if ("inactive-product-id".equals(productId)) {
            throw new ActiveSubscriptionValidationException("Subscription is not ACTIVE for product: " + productId);
        }

        // 2. Mock Plan Limit Validation
        // In the future, this will fetch limits from TMF620 and sum current usages from repository
        if ("exceeded-product-id".equals(productId)) {
            throw new PlanLimitExceededException("Usage exceeds plan limits for product: " + productId);
        }
    }
}
