package com.subscriptionengine.productorder.domain.service;

import com.subscriptionengine.productorder.domain.model.ProductOrder;
import com.subscriptionengine.productorder.domain.model.ProductOrderItem;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ProductOrderStateValidator {

    public void validateNewOrder(ProductOrder order) {
        if (order.getState() == null || "acknowledged".equalsIgnoreCase(order.getState())) {
            order.setState("ACKNOWLEDGED");
        } else {
            throw new IllegalStateException("New orders must start in Acknowledged state");
        }
        
        if (order.getProductOrderItem() == null || order.getProductOrderItem().isEmpty()) {
            throw new IllegalArgumentException("ProductOrder must have at least one ProductOrderItem");
        }

        for (ProductOrderItem item : order.getProductOrderItem()) {
            if (item.getAction() == null) {
                throw new IllegalArgumentException("ProductOrderItem must specify an action (add, modify, delete, noChange)");
            }
            if (item.getState() == null || "acknowledged".equalsIgnoreCase(item.getState())) {
                item.setState("ACKNOWLEDGED");
            } else {
                throw new IllegalStateException("New order items must start in Acknowledged state");
            }
        }
    }

    public void validateStateTransition(ProductOrder existingOrder, ProductOrder updatedOrder) {
        String oldState = existingOrder.getState();
        String newState = updatedOrder.getState();

        if (oldState.equalsIgnoreCase(newState)) {
            return; // No transition
        }

        if ("completed".equalsIgnoreCase(oldState) || "cancelled".equalsIgnoreCase(oldState) || "failed".equalsIgnoreCase(oldState)) {
            throw new IllegalStateException("Cannot transition from terminal state: " + oldState);
        }

        if ("completed".equalsIgnoreCase(newState)) {
            updatedOrder.setCompletionDate(OffsetDateTime.now());
            if (updatedOrder.getProductOrderItem() != null) {
                updatedOrder.getProductOrderItem().forEach(item -> item.setState("COMPLETED"));
            }
        } else if ("cancelled".equalsIgnoreCase(newState)) {
            updatedOrder.setCancellationDate(OffsetDateTime.now());
            if (updatedOrder.getCancellationReason() == null) {
                updatedOrder.setCancellationReason("Cancelled by user request");
            }
            if (updatedOrder.getProductOrderItem() != null) {
                updatedOrder.getProductOrderItem().forEach(item -> item.setState("CANCELLED"));
            }
        } else if ("in_progress".equalsIgnoreCase(newState) || "inprogress".equalsIgnoreCase(newState)) {
            if (!"acknowledged".equalsIgnoreCase(oldState) && !"pending".equalsIgnoreCase(oldState)) {
                 throw new IllegalStateException("Cannot transition to InProgress from " + oldState);
            }
            if (updatedOrder.getProductOrderItem() != null) {
                updatedOrder.getProductOrderItem().forEach(item -> item.setState("InProgress"));
            }
        }
    }
}
