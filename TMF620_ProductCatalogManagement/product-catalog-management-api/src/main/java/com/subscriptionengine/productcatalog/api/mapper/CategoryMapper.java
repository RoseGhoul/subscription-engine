package com.subscriptionengine.productcatalog.api.mapper;

import com.subscriptionengine.productcatalog.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.CategoryCreate;
import org.openapitools.model.CategoryUpdate;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface CategoryMapper {

    Category toDomain(CategoryCreate createDto);
    
    Category toDomain(CategoryUpdate updateDto);

    org.openapitools.model.Category toDto(Category domain);
}
