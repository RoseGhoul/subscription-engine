package com.subscriptionengine.productcatalog.api.mapper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateTimeMapper {

    public Instant map(OffsetDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toInstant();
    }

    public OffsetDateTime map(Instant value) {
        if (value == null) {
            return null;
        }
        return value.atOffset(ZoneOffset.UTC);
    }
}
