package com.subscriptionengine.processflow.infrastructure.adapter.in.web;
import com.subscriptionengine.processflow.application.mapper.ProcessExecutionMapper;
import com.subscriptionengine.processflow.application.port.in.ProcessExecutionUseCase;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProcessExecutionApi;
import org.openapitools.model.ProcessExecution;
import org.openapitools.model.ProcessExecutionCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
public class ProcessExecutionController implements ProcessExecutionApi {
    private final ProcessExecutionUseCase processExecutionUseCase;
    private final ProcessExecutionMapper processExecutionMapper;
    @Override
    public ResponseEntity<ProcessExecution> createProcessExecution(ProcessExecutionCreate processExecutionCreate) {
        var domain = processExecutionMapper.dtoToDomain(processExecutionCreate);
        var saved = processExecutionUseCase.createProcessExecution(domain);
        return new ResponseEntity<>(processExecutionMapper.domainToDto(saved), HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<ProcessExecution>> listProcessExecution() {
        var executions = processExecutionUseCase.listProcessExecutions().stream()
                .map(processExecutionMapper::domainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(executions);
    }
    @Override
    public ResponseEntity<ProcessExecution> retrieveProcessExecution(String id) {
        var domain = processExecutionUseCase.getProcessExecution(id);
        return ResponseEntity.ok(processExecutionMapper.domainToDto(domain));
    }
}
