package com.subscriptionengine.payment.domain.repository;

import com.subscriptionengine.payment.domain.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(String id);
    List<Payment> findAll(int page, int size);
}
