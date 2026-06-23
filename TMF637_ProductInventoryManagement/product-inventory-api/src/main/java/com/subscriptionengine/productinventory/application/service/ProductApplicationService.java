package com.subscriptionengine.productinventory.application.service;

import com.subscriptionengine.productinventory.domain.model.Product;
import com.subscriptionengine.productinventory.domain.repository.ProductRepository;
import com.subscriptionengine.productinventory.domain.event.ProductEvent;
import com.subscriptionengine.productinventory.domain.port.ProductEventPublisher;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductApplicationService {

    private final ProductRepository repository;
    private final ProductEventPublisher eventPublisher;

    public ProductApplicationService(ProductRepository repository, ProductEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Product createProduct(Product product) {
        validateProduct(product, null);
        
        if (product.getStatus() == null) {
            product.setStatus("created");
        }

        Product savedProduct = repository.save(product);
        eventPublisher.publish(ProductEvent.createEvent(savedProduct));
        return savedProduct;
    }

    public Optional<Product> getProduct(String id) {
        return repository.findById(id);
    }

    public Product patchProduct(String id, Product patch) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        
        validateProduct(patch, id);
        
        boolean statusChanged = false;
        
        if (patch.getName() != null) {
            existing.setName(patch.getName());
        }
        if (patch.getDescription() != null) {
            existing.setDescription(patch.getDescription());
        }
        if (patch.getStatus() != null && !patch.getStatus().equals(existing.getStatus())) {
            existing.setStatus(patch.getStatus());
            statusChanged = true;
        }
        if (patch.getIsBundle() != null) {
            existing.setIsBundle(patch.getIsBundle());
        }
        if (patch.getIsCustomerVisible() != null) {
            existing.setIsCustomerVisible(patch.getIsCustomerVisible());
        }
        if (patch.getStartDate() != null) {
            existing.setStartDate(patch.getStartDate());
        }
        if (patch.getTerminationDate() != null) {
            existing.setTerminationDate(patch.getTerminationDate());
        }
        
        if (patch.getProductCharacteristic() != null && !patch.getProductCharacteristic().isEmpty()) {
            existing.setProductCharacteristic(patch.getProductCharacteristic());
        }
        
        if (patch.getProductPrice() != null && !patch.getProductPrice().isEmpty()) {
            existing.setProductPrice(patch.getProductPrice());
        }

        if (patch.getProductRelationship() != null && !patch.getProductRelationship().isEmpty()) {
            existing.setProductRelationship(patch.getProductRelationship());
        }

        if (patch.getPlace() != null && !patch.getPlace().isEmpty()) {
            existing.setPlace(patch.getPlace());
        }

        if (patch.getRelatedParty() != null && !patch.getRelatedParty().isEmpty()) {
            existing.setRelatedParty(patch.getRelatedParty());
        }
        
        Product savedProduct = repository.save(existing);
        
        if (statusChanged) {
            eventPublisher.publish(ProductEvent.stateChangeEvent(savedProduct));
        } else {
            eventPublisher.publish(ProductEvent.attributeValueChangeEvent(savedProduct));
        }
        
        return savedProduct;
    }

    private void validateProduct(Product product, String currentId) {
        if (product.getStartDate() != null && product.getTerminationDate() != null) {
            if (product.getStartDate().isAfter(product.getTerminationDate())) {
                throw new IllegalArgumentException("startDate cannot be after terminationDate");
            }
        }
        
        if (product.getProductSerialNumber() != null) {
            repository.findByProductSerialNumber(product.getProductSerialNumber()).ifPresent(existing -> {
                if (currentId == null || !existing.getId().equals(currentId)) {
                    throw new IllegalArgumentException("productSerialNumber must be unique");
                }
            });
        }
    }

    public org.springframework.data.domain.Page<Product> listProduct(String name, String status, Boolean isBundle, org.springframework.data.domain.Pageable pageable) {
        return repository.listProduct(name, status, isBundle, pageable);
    }

    public void deleteProduct(String id) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found for deletion"));
        repository.deleteById(id);
        eventPublisher.publish(ProductEvent.deleteEvent(existing));
    }
}
