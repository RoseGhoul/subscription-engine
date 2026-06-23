package com.subscriptionengine.communication.application.port.in;

import com.subscriptionengine.communication.domain.model.CommunicationMessage;
import java.util.List;

public interface RetrieveCommunicationUseCase {
    CommunicationMessage getCommunicationMessage(String id);
    List<CommunicationMessage> listCommunicationMessages(Integer offset, Integer limit, String fields);
}
