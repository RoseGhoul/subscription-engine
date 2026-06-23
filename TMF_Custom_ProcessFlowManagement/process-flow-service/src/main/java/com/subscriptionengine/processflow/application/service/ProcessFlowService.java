package com.subscriptionengine.processflow.application.service;
import com.subscriptionengine.processflow.application.port.in.ProcessFlowUseCase;
import com.subscriptionengine.processflow.application.port.out.ProcessFlowPort;
import com.subscriptionengine.processflow.domain.exception.ResourceNotFoundException;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProcessFlowService implements ProcessFlowUseCase {
    private final ProcessFlowPort processFlowPort;
    @Override
    @Transactional
    public ProcessFlow createProcessFlow(ProcessFlow processFlow) {
        return processFlowPort.save(processFlow);
    }
    @Override
    @Transactional(readOnly = true)
    public List<ProcessFlow> listProcessFlows() {
        return processFlowPort.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public ProcessFlow getProcessFlow(String id) {
        return processFlowPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProcessFlow not found with id: " + id));
    }
}
