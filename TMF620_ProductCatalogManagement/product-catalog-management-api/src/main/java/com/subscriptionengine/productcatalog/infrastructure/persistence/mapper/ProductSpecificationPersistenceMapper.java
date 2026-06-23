package com.subscriptionengine.productcatalog.infrastructure.persistence.mapper;

import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductSpecificationJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductSpecificationPersistenceMapper {
    
    ProductSpecification toDomain(ProductSpecificationJpaEntity entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ProductSpecificationJpaEntity toEntity(ProductSpecification domain);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntityFromDomain(ProductSpecification domain, @org.mapstruct.MappingTarget ProductSpecificationJpaEntity entity);
}
