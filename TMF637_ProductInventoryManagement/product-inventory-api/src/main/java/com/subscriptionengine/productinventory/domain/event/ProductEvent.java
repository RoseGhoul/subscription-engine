package com.subscriptionengine.productinventory.domain.event;

import com.subscriptionengine.productinventory.domain.model.Product;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class ProductEvent {

    private final String eventId;
    private final OffsetDateTime eventTime;
    private final String eventType;
    private final Product product;

    public ProductEvent(String eventType, Product product) {
        this.eventId = UUID.randomUUID().toString();
        this.eventTime = OffsetDateTime.now();
        this.eventType = eventType;
        this.product = product;
    }

    public static ProductEvent createEvent(Product product) {
        return new ProductEvent("ProductCreateEvent", product);
    }

    public static ProductEvent stateChangeEvent(Product product) {
        return new ProductEvent("ProductStateChangeEvent", product);
    }

    public static ProductEvent attributeValueChangeEvent(Product product) {
        return new ProductEvent("ProductAttributeValueChangeEvent", product);
    }

    public static ProductEvent deleteEvent(Product product) {
        return new ProductEvent("ProductDeleteEvent", product);
    }
}
