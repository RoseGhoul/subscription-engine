package com.subscriptionengine.payment.api.mapper;

import com.subscriptionengine.payment.domain.model.Refund;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.RefundCreate;

@Mapper(componentModel = "spring")
public interface RefundApiMapper {

    @Mapping(target = "amountValue", source = "amount.value")
    @Mapping(target = "amountUnit", source = "amount.unit")
    Refund toDomain(RefundCreate refundCreate);

    @Mapping(target = "amount.value", source = "amountValue")
    @Mapping(target = "amount.unit", source = "amountUnit")
    org.openapitools.model.Refund toDto(Refund refund);
}
