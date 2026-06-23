package com.subscriptionengine.payment.application.service;

import com.subscriptionengine.payment.domain.model.Refund;
import com.subscriptionengine.payment.domain.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefundApplicationService {

    private final RefundRepository refundRepository;

    @Transactional
    public Refund createRefund(Refund refund) {
        refund.setId(UUID.randomUUID().toString());
        if (refund.getStatus() == null) {
            refund.setStatus("created");
        }
        return refundRepository.save(refund);
    }

    public Refund getRefund(String id) {
        return refundRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Refund not found"));
    }

    public List<Refund> listRefunds() {
        return refundRepository.findAll();
    }
}
