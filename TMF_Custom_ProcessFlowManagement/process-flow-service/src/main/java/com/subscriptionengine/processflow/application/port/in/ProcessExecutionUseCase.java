package com.subscriptionengine.processflow.application.port.in;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import java.util.List;
public interface ProcessExecutionUseCase {
    ProcessExecution createProcessExecution(ProcessExecution execution);
    List<ProcessExecution> listProcessExecutions();
    ProcessExecution getProcessExecution(String id);
}
