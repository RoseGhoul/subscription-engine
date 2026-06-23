package com.subscriptionengine.productorder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private Float percentage;
    private Float taxRate;
    private Money dutyFreeAmount;
    private Money taxIncludedAmount;
}
