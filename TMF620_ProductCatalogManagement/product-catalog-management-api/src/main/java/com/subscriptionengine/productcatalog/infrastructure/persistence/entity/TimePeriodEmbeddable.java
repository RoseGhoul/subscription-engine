package com.subscriptionengine.productcatalog.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriodEmbeddable {
    private Instant startDateTime;
    private Instant endDateTime;
}
