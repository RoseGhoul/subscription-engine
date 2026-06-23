package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedPartyRefJpaEntity {
    @Column(name = "party_id")
    private String id;
    
    @Column(name = "party_href")
    private String href;
    
    @Column(name = "party_name")
    private String name;
    
    @Column(name = "party_role")
    private String role;
}
