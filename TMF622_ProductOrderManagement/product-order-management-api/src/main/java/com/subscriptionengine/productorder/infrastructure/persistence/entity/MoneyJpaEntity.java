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
public class MoneyJpaEntity {

    @Column(name = "unit")
    private String unit;

    @Column(name = "value")
    private Float value;
}
