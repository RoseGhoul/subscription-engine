package com.subscriptionengine.payment.application.service;

import com.subscriptionengine.payment.domain.model.Payment;
import com.subscriptionengine.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    private final PaymentRepository paymentRepository;
    private final com.subscriptionengine.payment.application.event.PaymentEventPublisher paymentEventPublisher;

    @Transactional
    public Payment createPayment(Payment payment) {
        payment.setId(UUID.randomUUID().toString());
        if (payment.getStatus() == null) {
            payment.setStatus("created");
        }
        Payment savedPayment = paymentRepository.save(payment);
        paymentEventPublisher.publishPaymentCreatedEvent(savedPayment);
        return savedPayment;
    }

    public Payment getPayment(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    public List<Payment> listPayments(int page, int size) {
        return paymentRepository.findAll(page, size);
    }

}
