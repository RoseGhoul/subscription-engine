package com.subscriptionengine.communication.domain.model;

import lombok.Data;

@Data
public class Receiver {
    private String id;
    private String href;
    private String name;
    private String role;
    private String phoneNumber;
    private String emailAddress;
    private String referredType;
}
