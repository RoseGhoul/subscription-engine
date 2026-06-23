package com.subscriptionengine.communication.domain.model;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CommunicationMessage {
    private String id;
    private Long versionLock;
    private String href;
    private String description;
    private OffsetDateTime sendTime;
    private String state;
    private String subject;
    private String content;
    private CommunicationChannel channel;
    private List<Receiver> receiver;
    private CommunicationTemplateRef template;
    private List<Characteristic> characteristic;
    private String baseType;
    private String schemaLocation;
    private String type;
}
