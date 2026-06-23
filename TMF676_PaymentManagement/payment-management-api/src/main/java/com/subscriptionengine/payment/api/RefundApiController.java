package com.subscriptionengine.payment.api;

import com.subscriptionengine.payment.api.mapper.RefundApiMapper;
import com.subscriptionengine.payment.application.service.RefundApplicationService;
import com.subscriptionengine.payment.domain.model.Refund;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.RefundApi;
import org.openapitools.model.RefundCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmf-api/paymentManagement/v4")
@RequiredArgsConstructor
public class RefundApiController implements RefundApi {

    private final RefundApplicationService refundApplicationService;
    private final RefundApiMapper refundApiMapper;

    @Override
    public ResponseEntity<org.openapitools.model.Refund> createRefund(RefundCreate refundCreate) {
        Refund domainRefund = refundApiMapper.toDomain(refundCreate);
        Refund createdRefund = refundApplicationService.createRefund(domainRefund);
        org.openapitools.model.Refund responseDTO = refundApiMapper.toDto(createdRefund);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Refund>> listRefund(String fields, Integer offset, Integer limit) {
        List<Refund> domainRefunds = refundApplicationService.listRefunds();
        List<org.openapitools.model.Refund> responseDTOs = domainRefunds.stream()
                .map(refundApiMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Refund> retrieveRefund(String id, String fields) {
        Refund domainRefund = refundApplicationService.getRefund(id);
        org.openapitools.model.Refund responseDTO = refundApiMapper.toDto(domainRefund);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
