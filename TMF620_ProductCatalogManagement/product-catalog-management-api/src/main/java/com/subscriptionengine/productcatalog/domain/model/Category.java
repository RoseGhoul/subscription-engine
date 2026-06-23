package com.subscriptionengine.productcatalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String id;
    private String href;
    private String name;
    private String description;
    private Boolean isRoot;
    private String parentId;
    private Instant lastUpdate;
    private String lifecycleStatus;
    private String version;
    private TimePeriod validFor;
}
