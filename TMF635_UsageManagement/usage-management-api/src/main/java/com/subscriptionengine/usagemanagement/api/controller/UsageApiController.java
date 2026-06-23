package com.subscriptionengine.usagemanagement.api.controller;

import com.subscriptionengine.usagemanagement.api.mapper.UsageApiMapper;
import com.subscriptionengine.usagemanagement.application.service.UsageApplicationService;
import com.subscriptionengine.usagemanagement.domain.model.Usage;
import org.openapitools.api.UsageApi;
import org.openapitools.model.UsageCreate;
import org.openapitools.model.UsageUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tmf-api/usageManagement/v4")
public class UsageApiController implements UsageApi {

    private final UsageApplicationService service;
    private final UsageApiMapper mapper;

    public UsageApiController(UsageApplicationService service, UsageApiMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<org.openapitools.model.Usage> createUsage(UsageCreate usageCreate) {
        Usage domain = mapper.toDomain(usageCreate);
        Usage saved = service.createUsage(domain);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Usage> retrieveUsage(String id, String fields) {
        return service.getUsage(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<org.openapitools.model.Usage> patchUsage(String id, UsageUpdate usageUpdate) {
        Usage patch = mapper.toDomain(usageUpdate);
        try {
            Usage updated = service.patchUsage(id, patch);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<java.util.List<org.openapitools.model.Usage>> listUsage(String fields, Integer offset, Integer limit) {
        org.springframework.data.domain.Page<Usage> page = service.listUsage(fields, offset, limit);
        java.util.List<org.openapitools.model.Usage> usages = page.stream()
                .map(mapper::toDto)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Result-Count", String.valueOf(usages.size()))
                .body(usages);
    }

    @Override
    public ResponseEntity<Void> deleteUsage(String id) {
        service.deleteUsage(id);
        return ResponseEntity.noContent().build();
    }
}
