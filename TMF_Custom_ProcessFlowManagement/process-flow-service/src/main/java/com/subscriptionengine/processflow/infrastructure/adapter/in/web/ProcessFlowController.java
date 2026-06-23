package com.subscriptionengine.processflow.infrastructure.adapter.in.web;
import com.subscriptionengine.processflow.application.mapper.ProcessFlowMapper;
import com.subscriptionengine.processflow.application.port.in.ProcessFlowUseCase;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProcessFlowApi;
import org.openapitools.model.ProcessFlow;
import org.openapitools.model.ProcessFlowCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
public class ProcessFlowController implements ProcessFlowApi {
    private final ProcessFlowUseCase processFlowUseCase;
    private final ProcessFlowMapper processFlowMapper;
    @Override
    public ResponseEntity<ProcessFlow> createProcessFlow(ProcessFlowCreate processFlowCreate) {
        var domain = processFlowMapper.dtoToDomain(processFlowCreate);
        var saved = processFlowUseCase.createProcessFlow(domain);
        return new ResponseEntity<>(processFlowMapper.domainToDto(saved), HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<ProcessFlow>> listProcessFlow() {
        var flows = processFlowUseCase.listProcessFlows().stream()
                .map(processFlowMapper::domainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flows);
    }
    @Override
    public ResponseEntity<ProcessFlow> retrieveProcessFlow(String id) {
        var domain = processFlowUseCase.getProcessFlow(id);
        return ResponseEntity.ok(processFlowMapper.domainToDto(domain));
    }
}
