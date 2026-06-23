package com.subscriptionengine.productorder.api.mapper;

import com.subscriptionengine.productorder.domain.model.ProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.ProductOrderCreate;
import org.openapitools.model.ProductOrderUpdate;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrderApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "completionDate", ignore = true)
    @Mapping(target = "cancellationDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "billingAccountHref", source = "billingAccount.href")
    @Mapping(target = "billingAccountId", source = "billingAccount.id")
    @Mapping(target = "billingAccountName", source = "billingAccount.name")
    ProductOrder toDomain(ProductOrderCreate dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "completionDate", ignore = true)
    @Mapping(target = "cancellationDate", ignore = true)
    @Mapping(target = "billingAccountHref", source = "billingAccount.href")
    @Mapping(target = "billingAccountId", source = "billingAccount.id")
    @Mapping(target = "billingAccountName", source = "billingAccount.name")
    @Mapping(target = "state", expression = "java(dto.getState() != null ? dto.getState().getValue() : null)")
    ProductOrder toDomain(ProductOrderUpdate dto);

    @Mapping(target = "billingAccount.href", source = "billingAccountHref")
    @Mapping(target = "billingAccount.id", source = "billingAccountId")
    @Mapping(target = "billingAccount.name", source = "billingAccountName")
    org.openapitools.model.ProductOrder toDto(ProductOrder domain);

    @Mapping(target = "productOfferingHref", source = "productOffering.href")
    @Mapping(target = "productOfferingId", source = "productOffering.id")
    @Mapping(target = "productOfferingName", source = "productOffering.name")
    @Mapping(target = "action", expression = "java(dto.getAction() != null ? dto.getAction().getValue() : null)")
    @Mapping(target = "state", expression = "java(dto.getState() != null ? dto.getState().getValue() : null)")
    com.subscriptionengine.productorder.domain.model.ProductOrderItem toDomainItem(org.openapitools.model.ProductOrderItem dto);

    @Mapping(target = "productOffering.href", source = "productOfferingHref")
    @Mapping(target = "productOffering.id", source = "productOfferingId")
    @Mapping(target = "productOffering.name", source = "productOfferingName")
    org.openapitools.model.ProductOrderItem toDtoItem(com.subscriptionengine.productorder.domain.model.ProductOrderItem domain);

    default org.openapitools.model.ProductOrderStateType mapOrderState(String state) {
        if (state == null) return null;
        for (org.openapitools.model.ProductOrderStateType b : org.openapitools.model.ProductOrderStateType.values()) {
            if (b.getValue().equalsIgnoreCase(state)) return b;
        }
        return null;
    }

    default org.openapitools.model.ProductOrderItemStateType mapOrderItemState(String state) {
        if (state == null) return null;
        for (org.openapitools.model.ProductOrderItemStateType b : org.openapitools.model.ProductOrderItemStateType.values()) {
            if (b.getValue().equalsIgnoreCase(state)) return b;
        }
        return null;
    }

    default org.openapitools.model.OrderItemActionType mapOrderItemAction(String action) {
        if (action == null) return null;
        for (org.openapitools.model.OrderItemActionType b : org.openapitools.model.OrderItemActionType.values()) {
            if (b.getValue().equalsIgnoreCase(action)) return b;
        }
        return null;
    }
}
