package com.subscriptionengine.productcatalog.api.mapper;

import com.subscriptionengine.productcatalog.domain.model.ProductSpecification;
import org.mapstruct.Mapper;
import org.openapitools.model.ProductSpecificationCreate;
import org.openapitools.model.ProductSpecificationUpdate;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ProductSpecificationMapper {

    ProductSpecification toDomain(ProductSpecificationCreate createDto);
    
    ProductSpecification toDomain(ProductSpecificationUpdate updateDto);

    org.openapitools.model.ProductSpecification toDto(ProductSpecification domain);
}
