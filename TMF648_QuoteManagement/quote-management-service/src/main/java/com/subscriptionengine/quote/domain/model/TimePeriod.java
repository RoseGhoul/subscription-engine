package com.subscriptionengine.quote.domain.model;
import java.time.OffsetDateTime;
import lombok.Data;
@Data
public class TimePeriod {
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
}
