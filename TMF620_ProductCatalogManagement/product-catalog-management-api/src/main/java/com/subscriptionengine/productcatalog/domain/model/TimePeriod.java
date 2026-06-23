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
public class TimePeriod {
    private Instant startDateTime;
    private Instant endDateTime;
}
