package com.subscriptionengine.payment.infrastructure.mapper;

import com.subscriptionengine.payment.domain.model.Payment;
import com.subscriptionengine.payment.domain.model.Refund;
import com.subscriptionengine.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import com.subscriptionengine.payment.infrastructure.persistence.entity.RefundJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    PaymentJpaEntity toEntity(Payment domain);
    Payment toDomain(PaymentJpaEntity entity);
    
    void updateEntityFromDomain(Payment domain, @MappingTarget PaymentJpaEntity entity);

    RefundJpaEntity toEntity(Refund domain);
    Refund toDomain(RefundJpaEntity entity);
    
    void updateRefundEntityFromDomain(Refund domain, @MappingTarget RefundJpaEntity entity);
}
