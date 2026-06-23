package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedPartyRef {
    private String id;
    private String href;
    private String name;
    private String role;
}
