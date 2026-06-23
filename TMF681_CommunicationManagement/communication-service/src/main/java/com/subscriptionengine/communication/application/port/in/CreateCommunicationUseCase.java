package com.subscriptionengine.communication.application.port.in;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;

public interface CreateCommunicationUseCase {
    CommunicationMessage createCommunicationMessage(CommunicationMessage message);
}
