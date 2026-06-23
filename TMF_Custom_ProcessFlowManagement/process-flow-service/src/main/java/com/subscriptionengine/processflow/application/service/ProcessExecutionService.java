package com.subscriptionengine.processflow.application.service;
import com.subscriptionengine.processflow.application.port.in.ProcessExecutionUseCase;
import com.subscriptionengine.processflow.application.port.out.ProcessExecutionPort;
import com.subscriptionengine.processflow.domain.exception.ResourceNotFoundException;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import com.subscriptionengine.processflow.domain.model.ProcessExecutionState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProcessExecutionService implements ProcessExecutionUseCase {
    private final ProcessExecutionPort processExecutionPort;
    @Override
    @Transactional
    public ProcessExecution createProcessExecution(ProcessExecution execution) {
        execution.setState(ProcessExecutionState.CREATED);
        execution.setStartTime(OffsetDateTime.now());
        return processExecutionPort.save(execution);
    }
    @Override
    @Transactional(readOnly = true)
    public List<ProcessExecution> listProcessExecutions() {
        return processExecutionPort.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public ProcessExecution getProcessExecution(String id) {
        return processExecutionPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProcessExecution not found with id: " + id));
    }
}
