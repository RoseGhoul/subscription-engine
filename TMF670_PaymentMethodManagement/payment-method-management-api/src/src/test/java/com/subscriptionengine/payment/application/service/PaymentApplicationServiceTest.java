package com.subscriptionengine.payment.application.service;

import com.subscriptionengine.payment.domain.model.Payment;
import com.subscriptionengine.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentApplicationServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentApplicationService paymentApplicationService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setId("1");
        payment.setStatus("created");
        payment.setAmountValue(100.0f);
        payment.setAmountUnit("USD");
    }

    @Test
    void createPayment_Success() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentApplicationService.createPayment(new Payment());

        assertNotNull(createdPayment);
        assertEquals("created", createdPayment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void getPayment_Success() {
        when(paymentRepository.findById("1")).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentApplicationService.getPayment("1");

        assertNotNull(foundPayment);
        assertEquals("1", foundPayment.getId());
        verify(paymentRepository, times(1)).findById("1");
    }
}
