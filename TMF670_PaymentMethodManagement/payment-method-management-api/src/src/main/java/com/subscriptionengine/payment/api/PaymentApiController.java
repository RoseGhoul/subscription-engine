package com.subscriptionengine.payment.api;

import com.subscriptionengine.payment.api.mapper.PaymentApiMapper;
import com.subscriptionengine.payment.application.service.PaymentApplicationService;
import com.subscriptionengine.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PaymentApi;
import org.openapitools.model.PaymentCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmf-api/paymentManagement/v4")
@RequiredArgsConstructor
public class PaymentApiController implements PaymentApi {

    private final PaymentApplicationService paymentApplicationService;
    private final PaymentApiMapper paymentApiMapper;

    @Override
    public ResponseEntity<org.openapitools.model.Payment> createPayment(PaymentCreate paymentCreate) {
        Payment domainPayment = paymentApiMapper.toDomain(paymentCreate);
        Payment createdPayment = paymentApplicationService.createPayment(domainPayment);
        org.openapitools.model.Payment responseDTO = paymentApiMapper.toDto(createdPayment);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Payment>> listPayment(String fields, Integer offset, Integer limit) {
        int page = (offset != null && limit != null && limit > 0) ? offset / limit : 0;
        int size = (limit != null && limit > 0) ? limit : 20;
        
        List<Payment> domainPayments = paymentApplicationService.listPayments(page, size);
        List<org.openapitools.model.Payment> responseDTOs = domainPayments.stream()
                .map(paymentApiMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Payment> retrievePayment(String id, String fields) {
        Payment domainPayment = paymentApplicationService.getPayment(id);
        org.openapitools.model.Payment responseDTO = paymentApiMapper.toDto(domainPayment);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
