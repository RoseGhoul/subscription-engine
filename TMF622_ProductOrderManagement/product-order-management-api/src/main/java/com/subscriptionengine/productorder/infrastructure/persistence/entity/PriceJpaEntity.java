package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceJpaEntity {

    @Column(name = "percentage")
    private Float percentage;

    @Column(name = "tax_rate")
    private Float taxRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "unit", column = @Column(name = "duty_free_unit")),
            @AttributeOverride(name = "value", column = @Column(name = "duty_free_value"))
    })
    private MoneyJpaEntity dutyFreeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "unit", column = @Column(name = "tax_included_unit")),
            @AttributeOverride(name = "value", column = @Column(name = "tax_included_value"))
    })
    private MoneyJpaEntity taxIncludedAmount;
}
