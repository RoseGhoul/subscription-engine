package com.subscriptionengine.paymentmethod.domain.port.in;

import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodUseCase {
    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod getPaymentMethod(String id);
    List<PaymentMethod> listPaymentMethods();
    void deletePaymentMethod(String id);
    PaymentMethod updatePaymentMethod(String id, PaymentMethod paymentMethod);
}
