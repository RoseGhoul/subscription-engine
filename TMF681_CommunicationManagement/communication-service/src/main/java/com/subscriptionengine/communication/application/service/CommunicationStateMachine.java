package com.subscriptionengine.communication.application.service;

import com.subscriptionengine.communication.domain.exception.InvalidStateException;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.Map;

@Component
public class CommunicationStateMachine {
    private static final Map<String, Set<String>> VALID_TRANSITIONS = Map.of(
        "INITIALIZED", Set.of("IN_PROGRESS", "FAILED"),
        "IN_PROGRESS", Set.of("DELIVERED", "FAILED"),
        "DELIVERED", Set.of(),
        "FAILED", Set.of()
    );

    public void validateTransition(String currentState, String newState) {
        if (currentState == null || newState == null) return;
        if (currentState.equals(newState)) return;
        Set<String> allowed = VALID_TRANSITIONS.getOrDefault(currentState, Set.of());
        if (!allowed.contains(newState)) {
            throw new InvalidStateException("Cannot transition from " + currentState + " to " + newState + "");
        }
    }
}
