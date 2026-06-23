package com.subscriptionengine.processflow.domain.model;
import lombok.Data;
import java.util.List;
@Data
public class ProcessFlow {
    private String id;
    private String href;
    private String name;
    private String description;
    private String version;
    private List<ProcessStep> processStep;
}
