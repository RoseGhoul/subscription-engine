package com.subscriptionengine.paymentmethod.infrastructure.adapter.in.web;

import com.subscriptionengine.paymentmethod.application.mapper.PaymentMethodMapper;
import com.subscriptionengine.paymentmethod.domain.model.PaymentMethod;
import com.subscriptionengine.paymentmethod.domain.port.in.PaymentMethodUseCase;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PaymentMethodApi;
import org.openapitools.model.PaymentMethodCreate;
import org.openapitools.model.PaymentMethodUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentMethodApiController implements PaymentMethodApi {

    private final PaymentMethodUseCase useCase;
    private final PaymentMethodMapper mapper;

    @Override
    public ResponseEntity<org.openapitools.model.PaymentMethod> createPaymentMethod(PaymentMethodCreate paymentMethodCreate) {
        PaymentMethod domain = mapper.toDomain(paymentMethodCreate);
        PaymentMethod created = useCase.createPaymentMethod(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePaymentMethod(String id) {
        useCase.deletePaymentMethod(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.PaymentMethod>> listPaymentMethod(String fields, Integer offset, Integer limit) {
        List<org.openapitools.model.PaymentMethod> list = useCase.listPaymentMethods().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.PaymentMethod> patchPaymentMethod(String id, PaymentMethodUpdate paymentMethodUpdate) {
        PaymentMethod domainUpdate = mapper.toDomain(paymentMethodUpdate);
        PaymentMethod updated = useCase.updatePaymentMethod(id, domainUpdate);
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.PaymentMethod> retrievePaymentMethod(String id, String fields) {
        PaymentMethod found = useCase.getPaymentMethod(id);
        return new ResponseEntity<>(mapper.toDto(found), HttpStatus.OK);
    }
}
