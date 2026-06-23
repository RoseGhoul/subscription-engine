package com.subscriptionengine.paymentmethod.application.mapper;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.PaymentMethodCreate;
import org.openapitools.model.PaymentMethodUpdate;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethod toDomain(PaymentMethodCreate createDto);
    PaymentMethod toDomain(PaymentMethodUpdate updateDto);

    org.openapitools.model.PaymentMethod toDto(PaymentMethod domain);

    void updateDomainFromDto(PaymentMethodUpdate updateDto, @MappingTarget PaymentMethod domain);

    default String map(java.time.OffsetDateTime value) {
        return value != null ? value.toString() : null;
    }

    default java.time.OffsetDateTime map(String value) {
        return value != null ? java.time.OffsetDateTime.parse(value) : null;
    }

    default String map(java.net.URI value) {
        return value != null ? value.toString() : null;
    }

    default java.net.URI mapUri(String value) {
        return value != null ? java.net.URI.create(value) : null;
    }
}
