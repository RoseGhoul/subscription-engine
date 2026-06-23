package com.subscriptionengine.productorder.infrastructure.persistence.mapper;

import com.subscriptionengine.productorder.domain.model.ProductOrder;
import com.subscriptionengine.productorder.infrastructure.persistence.entity.ProductOrderJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrderPersistenceMapper {

    ProductOrderJpaEntity toEntity(ProductOrder domain);

    @org.mapstruct.AfterMapping
    default void linkOrderItems(@org.mapstruct.MappingTarget ProductOrderJpaEntity entity) {
        if (entity.getProductOrderItem() != null) {
            entity.getProductOrderItem().forEach(item -> {
                item.setProductOrder(entity);
                if (item.getItemPrice() != null) {
                    item.getItemPrice().forEach(price -> {
                        price.setProductOrderItem(item);
                        price.setItemPriceType("ITEM_PRICE");
                        if (price.getPriceAlteration() != null) {
                            price.getPriceAlteration().forEach(alt -> alt.setOrderPrice(price));
                        }
                    });
                }
                if (item.getItemTotalPrice() != null) {
                    item.getItemTotalPrice().forEach(price -> {
                        price.setProductOrderItem(item);
                        price.setItemPriceType("ITEM_TOTAL_PRICE");
                        if (price.getPriceAlteration() != null) {
                            price.getPriceAlteration().forEach(alt -> alt.setOrderPrice(price));
                        }
                    });
                }
            });
        }
        if (entity.getOrderTotalPrice() != null) {
            entity.getOrderTotalPrice().forEach(price -> {
                price.setProductOrder(entity);
                if (price.getPriceAlteration() != null) {
                    price.getPriceAlteration().forEach(alt -> alt.setOrderPrice(price));
                }
            });
        }
    }

    ProductOrder toDomain(ProductOrderJpaEntity entity);

    @Mapping(target = "createdDate", ignore = true)
    void updateEntityFromDomain(ProductOrder domain, @org.mapstruct.MappingTarget ProductOrderJpaEntity entity);
}
