package com.subscriptionengine.communication.application.service;

import com.subscriptionengine.communication.application.port.out.CommunicationMessageRepositoryPort;
import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommunicationMessageServiceTest {

    @Mock
    private CommunicationMessageRepositoryPort repositoryPort;

    @Mock
    private CommunicationStateMachine stateMachine;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CommunicationMessageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCommunicationMessage_ShouldSetStateToInitialized() {
        CommunicationMessage msg = new CommunicationMessage();
        CommunicationMessage saved = new CommunicationMessage();
        saved.setState("INITIALIZED");
        
        when(repositoryPort.save(any(CommunicationMessage.class))).thenReturn(saved);
        
        CommunicationMessage result = service.createCommunicationMessage(msg);
        assertEquals("INITIALIZED", result.getState());
    }
}
