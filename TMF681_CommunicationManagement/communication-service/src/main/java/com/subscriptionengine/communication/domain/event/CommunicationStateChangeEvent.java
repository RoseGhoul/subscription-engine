package com.subscriptionengine.communication.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CommunicationStateChangeEvent extends ApplicationEvent {
    private final String communicationId;
    private final String oldState;
    private final String newState;

    public CommunicationStateChangeEvent(Object source, String communicationId, String oldState, String newState) {
        super(source);
        this.communicationId = communicationId;
        this.oldState = oldState;
        this.newState = newState;
    }
}
