package com.subscriptionengine.payment.domain.repository;

import com.subscriptionengine.payment.domain.model.Refund;
import java.util.List;
import java.util.Optional;

public interface RefundRepository {
    Refund save(Refund refund);
    Optional<Refund> findById(String id);
    List<Refund> findAll();
}
