package com.subscriptionengine.paymentmethod.domain.port.out;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepositoryPort {
    PaymentMethod save(PaymentMethod paymentMethod);
    Optional<PaymentMethod> findById(String id);
    List<PaymentMethod> findAll();
    void deleteById(String id);
}
