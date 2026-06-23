package com.subscriptionengine.processflow.application.port.out;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import java.util.List;
import java.util.Optional;
public interface ProcessExecutionPort {
    ProcessExecution save(ProcessExecution execution);
    List<ProcessExecution> findAll();
    Optional<ProcessExecution> findById(String id);
}
