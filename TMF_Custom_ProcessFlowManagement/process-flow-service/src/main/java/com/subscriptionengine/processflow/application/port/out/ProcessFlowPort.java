package com.subscriptionengine.processflow.application.port.out;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import java.util.List;
import java.util.Optional;
public interface ProcessFlowPort {
    ProcessFlow save(ProcessFlow processFlow);
    List<ProcessFlow> findAll();
    Optional<ProcessFlow> findById(String id);
}
