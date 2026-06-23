package com.subscriptionengine.productorder.application.service;

import com.subscriptionengine.productorder.application.port.in.ManageProductOrderUseCase;
import com.subscriptionengine.productorder.application.port.out.EventPublisher;
import com.subscriptionengine.productorder.application.port.out.ProductOrderRepositoryPort;
import com.subscriptionengine.productorder.domain.event.ProductOrderCreatedEvent;
import com.subscriptionengine.productorder.domain.event.ProductOrderStateChangedEvent;
import com.subscriptionengine.productorder.domain.model.ProductOrder;
import com.subscriptionengine.productorder.domain.service.ProductOrderStateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductOrderApplicationService implements ManageProductOrderUseCase {

    private final ProductOrderRepositoryPort repositoryPort;
    private final ProductOrderStateValidator stateValidator;
    private final EventPublisher eventPublisher;

    @Override
    public ProductOrder createProductOrder(ProductOrder productOrder) {
        productOrder.setOrderDate(OffsetDateTime.now());
        
        stateValidator.validateNewOrder(productOrder);
        
        if (productOrder.getProductOrderItem() != null) {
            productOrder.getProductOrderItem().forEach(item -> {
                if (item.getId() == null) {
                    item.setId(UUID.randomUUID().toString());
                }
            });
        }
        if (productOrder.getNote() != null) {
            productOrder.getNote().forEach(note -> {
                if (note.getId() == null) {
                    note.setId(UUID.randomUUID().toString());
                }
                if (note.getDate() == null) {
                    note.setDate(OffsetDateTime.now());
                }
            });
        }
        ProductOrder saved = repositoryPort.save(productOrder);
        eventPublisher.publishProductOrderCreatedEvent(new ProductOrderCreatedEvent(saved));
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductOrder getProductOrderById(UUID id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductOrder not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductOrder> listProductOrders(java.util.Map<String, String> filters, Integer offset, Integer limit) {
        return repositoryPort.findAll(filters, offset, limit);
    }

    @Override
    public ProductOrder updateProductOrder(UUID id, ProductOrder productOrder) {
        ProductOrder existing = getProductOrderById(id);
        boolean stateChanged = false;
        String oldState = existing.getState();
        
        if (productOrder.getState() != null) {
            stateValidator.validateStateTransition(existing, productOrder);
            if (!productOrder.getState().equalsIgnoreCase(oldState)) {
                stateChanged = true;
            }
            existing.setState(productOrder.getState());
            if (productOrder.getCompletionDate() != null) existing.setCompletionDate(productOrder.getCompletionDate());
            if (productOrder.getCancellationDate() != null) existing.setCancellationDate(productOrder.getCancellationDate());
            if (productOrder.getCancellationReason() != null) existing.setCancellationReason(productOrder.getCancellationReason());
        }
        
        if (productOrder.getDescription() != null) existing.setDescription(productOrder.getDescription());
        if (productOrder.getCategory() != null) existing.setCategory(productOrder.getCategory());
        if (productOrder.getPriority() != null) existing.setPriority(productOrder.getPriority());
        if (productOrder.getNotificationContact() != null) existing.setNotificationContact(productOrder.getNotificationContact());
        
        if (productOrder.getNote() != null) {
            productOrder.getNote().forEach(note -> {
                if (note.getId() == null) note.setId(UUID.randomUUID().toString());
                if (note.getDate() == null) note.setDate(OffsetDateTime.now());
            });
            existing.setNote(productOrder.getNote());
        }
        if (productOrder.getProductOrderItem() != null) {
            productOrder.getProductOrderItem().forEach(item -> {
                if (item.getId() == null) item.setId(UUID.randomUUID().toString());
            });
            existing.setProductOrderItem(productOrder.getProductOrderItem());
        }
        if (productOrder.getRelatedParty() != null) existing.setRelatedParty(productOrder.getRelatedParty());
        
        // Let's assume order item states can also be updated through state transitions above
        
        ProductOrder saved = repositoryPort.save(existing);
        
        if (stateChanged) {
            eventPublisher.publishProductOrderStateChangedEvent(new ProductOrderStateChangedEvent(saved, oldState, saved.getState()));
        }
        
        return saved;
    }

    @Override
    public void deleteProductOrder(UUID id) {
        repositoryPort.deleteById(id);
    }
}
