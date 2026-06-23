package com.subscriptionengine.processflow.application.port.in;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import java.util.List;
public interface ProcessFlowUseCase {
    ProcessFlow createProcessFlow(ProcessFlow processFlow);
    List<ProcessFlow> listProcessFlows();
    ProcessFlow getProcessFlow(String id);
}
