package com.subscriptionengine.communication.application.service;

import com.subscriptionengine.communication.application.port.in.CreateCommunicationUseCase;
import com.subscriptionengine.communication.application.port.in.RetrieveCommunicationUseCase;
import com.subscriptionengine.communication.application.port.in.UpdateCommunicationUseCase;
import com.subscriptionengine.communication.application.port.out.CommunicationMessageRepositoryPort;
import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import com.subscriptionengine.communication.domain.exception.ResourceNotFoundException;
import com.subscriptionengine.communication.domain.event.CommunicationStateChangeEvent;
import org.springframework.context.ApplicationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationMessageService implements CreateCommunicationUseCase, RetrieveCommunicationUseCase, UpdateCommunicationUseCase {

    private final CommunicationMessageRepositoryPort repositoryPort;
    private final CommunicationStateMachine stateMachine;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CommunicationMessage createCommunicationMessage(CommunicationMessage message) {
        message.setState("INITIALIZED");
        return repositoryPort.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public CommunicationMessage getCommunicationMessage(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommunicationMessage with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationMessage> listCommunicationMessages(Integer offset, Integer limit, String fields) {
        return repositoryPort.findAll(offset, limit);
    }

    @Override
    @Transactional
    public CommunicationMessage patchCommunicationMessage(String id, CommunicationMessage message) {
        CommunicationMessage existing = getCommunicationMessage(id);
        if (message.getState() != null) {
            stateMachine.validateTransition(existing.getState(), message.getState());
            eventPublisher.publishEvent(new CommunicationStateChangeEvent(this, id, existing.getState(), message.getState()));
            existing.setState(message.getState());
        }
        if (message.getSendTime() != null) {
            existing.setSendTime(message.getSendTime());
        }
        return repositoryPort.save(existing);
    }
}
