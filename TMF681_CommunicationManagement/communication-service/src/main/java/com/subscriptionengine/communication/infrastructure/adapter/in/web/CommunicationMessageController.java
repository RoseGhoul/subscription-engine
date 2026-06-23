package com.subscriptionengine.communication.infrastructure.adapter.in.web;

import com.subscriptionengine.communication.application.port.in.CreateCommunicationUseCase;
import com.subscriptionengine.communication.application.port.in.RetrieveCommunicationUseCase;
import com.subscriptionengine.communication.application.port.in.UpdateCommunicationUseCase;
import com.subscriptionengine.communication.infrastructure.adapter.in.web.mapper.CommunicationMessageWebMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.DefaultApi;
import org.openapitools.model.CommunicationMessage;
import org.openapitools.model.CommunicationMessageCreate;
import org.openapitools.model.CommunicationMessageUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/tmf-api/communicationManagement/v4")
@RequiredArgsConstructor
public class CommunicationMessageController implements DefaultApi {

    private final CreateCommunicationUseCase createUseCase;
    private final RetrieveCommunicationUseCase retrieveUseCase;
    private final UpdateCommunicationUseCase updateUseCase;
    private final CommunicationMessageWebMapper mapper;

    @Override
    public ResponseEntity<CommunicationMessage> createCommunicationMessage(CommunicationMessageCreate communicationMessageCreate) {
        var domain = mapper.toDomain(communicationMessageCreate);
        var created = createUseCase.createCommunicationMessage(domain);
        return new ResponseEntity<>(mapper.toDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CommunicationMessage>> listCommunicationMessage(String fields, Integer offset, Integer limit) {
        var domains = retrieveUseCase.listCommunicationMessages(offset, limit, fields);
        var dtos = domains.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<CommunicationMessage> patchCommunicationMessage(String id, CommunicationMessageUpdate communicationMessageUpdate) {
        var domain = mapper.toDomain(communicationMessageUpdate);
        var updated = updateUseCase.patchCommunicationMessage(id, domain);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Override
    public ResponseEntity<CommunicationMessage> retrieveCommunicationMessage(String id, String fields) {
        var domain = retrieveUseCase.getCommunicationMessage(id);
        return ResponseEntity.ok(mapper.toDto(domain));
    }
}
