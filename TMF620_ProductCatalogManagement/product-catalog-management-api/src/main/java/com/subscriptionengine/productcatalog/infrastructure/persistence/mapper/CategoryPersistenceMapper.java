package com.subscriptionengine.productcatalog.infrastructure.persistence.mapper;

import com.subscriptionengine.productcatalog.domain.model.Category;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.CategoryJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    
    Category toDomain(CategoryJpaEntity entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CategoryJpaEntity toEntity(Category domain);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntityFromDomain(Category domain, @org.mapstruct.MappingTarget CategoryJpaEntity entity);
}
