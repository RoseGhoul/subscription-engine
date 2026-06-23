package com.subscriptionengine.usagemanagement.api.mapper;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.UsageCreate;
import org.openapitools.model.UsageUpdate;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsageApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "status", expression = "java(dto.getStatus() != null ? dto.getStatus().getValue() : null)")
    @Mapping(target = "usageCharacteristic", expression = "java(mapCharacteristics(dto.getUsageCharacteristic()))")
    Usage toDomain(UsageCreate dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "status", expression = "java(dto.getStatus() != null ? dto.getStatus().getValue() : null)")
    @Mapping(target = "usageCharacteristic", expression = "java(mapCharacteristics(dto.getUsageCharacteristic()))")
    Usage toDomain(UsageUpdate dto);

    @Mapping(target = "usageCharacteristic", expression = "java(mapCharacteristicsToDto(domain.getUsageCharacteristic()))")
    org.openapitools.model.Usage toDto(Usage domain);

    default java.util.List<com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic> mapCharacteristics(java.util.List<org.openapitools.model.UsageCharacteristic> dtos) {
        if (dtos == null) return null;
        java.util.List<com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic> list = new java.util.ArrayList<>();
        for (org.openapitools.model.UsageCharacteristic dto : dtos) {
            com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic c = new com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic();
            c.setName(dto.getName());
            c.setValueType(dto.getValueType());
            c.setValue(dto.getValue() != null ? dto.getValue().toString() : null);
            list.add(c);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.UsageCharacteristic> mapCharacteristicsToDto(java.util.List<com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.UsageCharacteristic> list = new java.util.ArrayList<>();
        for (com.subscriptionengine.usagemanagement.domain.model.UsageCharacteristic model : models) {
            org.openapitools.model.UsageCharacteristic c = new org.openapitools.model.UsageCharacteristic();
            c.setName(model.getName());
            c.setValueType(model.getValueType());
            c.setValue(model.getValue());
            list.add(c);
        }
        return list;
    }

    default org.openapitools.model.UsageStatusType mapUsageStatus(String status) {
        return status == null ? null : org.openapitools.model.UsageStatusType.fromValue(status);
    }

    default String map(java.net.URI value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    default java.net.URI map(String value) {
        if (value == null) {
            return null;
        }
        return java.net.URI.create(value);
    }

}
