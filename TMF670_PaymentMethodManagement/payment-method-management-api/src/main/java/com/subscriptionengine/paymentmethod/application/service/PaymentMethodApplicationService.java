package com.subscriptionengine.paymentmethod.application.service;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;
import com.subscriptionengine.paymentmethod.domain.port.in.PaymentMethodUseCase;
import com.subscriptionengine.paymentmethod.domain.port.out.PaymentMethodRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodApplicationService implements PaymentMethodUseCase {

    private final PaymentMethodRepositoryPort repositoryPort;

    @Override
    @Transactional
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        log.info("Creating payment method: {}", paymentMethod.getName());
        paymentMethod.setId(UUID.randomUUID().toString());
        paymentMethod.setHref("/paymentMethodManagement/v4/paymentMethod/" + paymentMethod.getId());
        paymentMethod.setStatus("active");
        paymentMethod.setStatusDate(OffsetDateTime.now().toString());

        return repositoryPort.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethod(String id) {
        return repositoryPort.findById(id).orElseThrow(() -> new RuntimeException("PaymentMethod not found: " + id));
    }

    @Override
    public List<PaymentMethod> listPaymentMethods() {
        return repositoryPort.findAll();
    }

    @Override
    @Transactional
    public void deletePaymentMethod(String id) {
        log.info("Deleting payment method: {}", id);
        repositoryPort.deleteById(id);
    }

    @Override
    @Transactional
    public PaymentMethod updatePaymentMethod(String id, PaymentMethod updateData) {
        PaymentMethod existing = getPaymentMethod(id);
        
        if (updateData.getName() != null) {
            existing.setName(updateData.getName());
        }
        if (updateData.getStatus() != null) {
            existing.setStatus(updateData.getStatus());
            existing.setStatusDate(OffsetDateTime.now().toString());
        }
        
        return repositoryPort.save(existing);
    }
}
