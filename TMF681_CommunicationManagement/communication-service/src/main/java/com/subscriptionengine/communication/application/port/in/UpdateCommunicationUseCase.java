package com.subscriptionengine.communication.application.port.in;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;

public interface UpdateCommunicationUseCase {
    CommunicationMessage patchCommunicationMessage(String id, CommunicationMessage message);
}
