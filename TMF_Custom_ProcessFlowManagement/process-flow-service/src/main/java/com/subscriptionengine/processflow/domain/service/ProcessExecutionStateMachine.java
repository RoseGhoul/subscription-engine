package com.subscriptionengine.processflow.domain.service;
import com.subscriptionengine.processflow.domain.exception.InvalidStateException;
import com.subscriptionengine.processflow.domain.model.ProcessExecutionState;
import org.springframework.stereotype.Service;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
@Service
public class ProcessExecutionStateMachine {
    private static final Map<ProcessExecutionState, Set<ProcessExecutionState>> ALLOWED_TRANSITIONS = Map.of(
            ProcessExecutionState.CREATED, EnumSet.of(ProcessExecutionState.RUNNING, ProcessExecutionState.FAILED),
            ProcessExecutionState.RUNNING, EnumSet.of(ProcessExecutionState.WAITING, ProcessExecutionState.COMPLETED, ProcessExecutionState.FAILED),
            ProcessExecutionState.WAITING, EnumSet.of(ProcessExecutionState.RUNNING, ProcessExecutionState.FAILED),
            ProcessExecutionState.COMPLETED, EnumSet.noneOf(ProcessExecutionState.class),
            ProcessExecutionState.FAILED, EnumSet.of(ProcessExecutionState.COMPENSATED),
            ProcessExecutionState.COMPENSATED, EnumSet.noneOf(ProcessExecutionState.class)
    );
    public void validateTransition(ProcessExecutionState currentState, ProcessExecutionState nextState) {
        if (currentState == null || nextState == null) {
            throw new InvalidStateException("States cannot be null");
        }
        if (!ALLOWED_TRANSITIONS.getOrDefault(currentState, EnumSet.noneOf(ProcessExecutionState.class)).contains(nextState)) {
            throw new InvalidStateException("Invalid transition from " + currentState + " to " + nextState);
        }
    }
}
