package com.subscriptionengine.payment.api.mapper;

import com.subscriptionengine.payment.domain.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.PaymentCreate;

@Mapper(componentModel = "spring")
public interface PaymentApiMapper {

    @Mapping(target = "amountValue", source = "amount.value")
    @Mapping(target = "amountUnit", source = "amount.unit")
    Payment toDomain(PaymentCreate paymentCreate);

    @Mapping(target = "amount.value", source = "amountValue")
    @Mapping(target = "amount.unit", source = "amountUnit")
    org.openapitools.model.Payment toDto(Payment payment);
}
