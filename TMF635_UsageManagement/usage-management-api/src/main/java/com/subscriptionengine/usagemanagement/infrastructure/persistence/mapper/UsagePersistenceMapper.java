package com.subscriptionengine.usagemanagement.infrastructure.persistence.mapper;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import com.subscriptionengine.usagemanagement.infrastructure.persistence.entity.UsageJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsagePersistenceMapper {

    Usage toDomain(UsageJpaEntity entity);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    UsageJpaEntity toEntity(Usage domain);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(Usage domain, @MappingTarget UsageJpaEntity entity);

    @Mapping(target = "taxExcludedRatingAmount.unit", source = "taxExcludedRatingAmountUnit")
    @Mapping(target = "taxExcludedRatingAmount.value", source = "taxExcludedRatingAmountValue")
    @Mapping(target = "taxIncludedRatingAmount.unit", source = "taxIncludedRatingAmountUnit")
    @Mapping(target = "taxIncludedRatingAmount.value", source = "taxIncludedRatingAmountValue")
    @Mapping(target = "productRef.id", source = "productRefId")
    @Mapping(target = "productRef.href", source = "productRefHref")
    @Mapping(target = "productRef.name", source = "productRefName")
    com.subscriptionengine.usagemanagement.domain.model.RatedProductUsage toDomain(com.subscriptionengine.usagemanagement.infrastructure.persistence.entity.RatedProductUsageJpaEntity entity);

    @Mapping(source = "taxExcludedRatingAmount.unit", target = "taxExcludedRatingAmountUnit")
    @Mapping(source = "taxExcludedRatingAmount.value", target = "taxExcludedRatingAmountValue")
    @Mapping(source = "taxIncludedRatingAmount.unit", target = "taxIncludedRatingAmountUnit")
    @Mapping(source = "taxIncludedRatingAmount.value", target = "taxIncludedRatingAmountValue")
    @Mapping(source = "productRef.id", target = "productRefId")
    @Mapping(source = "productRef.href", target = "productRefHref")
    @Mapping(source = "productRef.name", target = "productRefName")
    com.subscriptionengine.usagemanagement.infrastructure.persistence.entity.RatedProductUsageJpaEntity toEntity(com.subscriptionengine.usagemanagement.domain.model.RatedProductUsage domain);
}
