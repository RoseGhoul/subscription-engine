package com.subscriptionengine.quote.application.mapper;
import com.subscriptionengine.quote.domain.model.Quote;
import com.subscriptionengine.quote.infrastructure.adapter.out.persistence.entity.QuoteJpaEntity;
import org.mapstruct.*;
import org.openapitools.model.QuoteCreate;
import org.openapitools.model.QuoteUpdate;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuoteMapper {
    // DTO -> Domain
    Quote createDtoToDomain(QuoteCreate dto);
    Quote updateDtoToDomain(QuoteUpdate dto);
    org.openapitools.model.Quote domainToDto(Quote domain);

    // Domain -> Entity
    @Mapping(target = "validForStartDateTime", source = "validFor.startDateTime")
    @Mapping(target = "validForEndDateTime", source = "validFor.endDateTime")
    QuoteJpaEntity domainToEntity(Quote domain);

    // Entity -> Domain
    @Mapping(target = "validFor.startDateTime", source = "validForStartDateTime")
    @Mapping(target = "validFor.endDateTime", source = "validForEndDateTime")
    Quote entityToDomain(QuoteJpaEntity entity);

    @Mapping(target = "validForStartDateTime", source = "validFor.startDateTime")
    @Mapping(target = "validForEndDateTime", source = "validFor.endDateTime")
    void updateEntityFromDomain(Quote domain, @MappingTarget QuoteJpaEntity entity);

    default Instant map(OffsetDateTime value) {
        return value != null ? value.toInstant() : null;
    }
    default OffsetDateTime map(Instant value) {
        return value != null ? value.atOffset(ZoneOffset.UTC) : null;
    }

    default com.subscriptionengine.quote.domain.model.Quantity mapQuantity(Integer value) {
        if (value == null) return null;
        com.subscriptionengine.quote.domain.model.Quantity q = new com.subscriptionengine.quote.domain.model.Quantity();
        q.setAmount(value.floatValue());
        q.setUnits("unit");
        return q;
    }

    default Integer mapQuantity(com.subscriptionengine.quote.domain.model.Quantity value) {
        if (value == null || value.getAmount() == null) return null;
        return value.getAmount().intValue();
    }
}
