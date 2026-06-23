package com.subscriptionengine.communication.application.port.out;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import java.util.List;
import java.util.Optional;

public interface CommunicationMessageRepositoryPort {
    CommunicationMessage save(CommunicationMessage message);
    Optional<CommunicationMessage> findById(String id);
    List<CommunicationMessage> findAll(Integer offset, Integer limit);
}
