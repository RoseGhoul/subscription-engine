package com.subscriptionengine.productinventory.infrastructure.mapper;

import com.subscriptionengine.productinventory.domain.model.Product;
import com.subscriptionengine.productinventory.infrastructure.entity.ProductJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper {

    @Mapping(target = "productOffering.id", source = "productOfferingId")
    @Mapping(target = "productOffering.name", source = "productOfferingName")
    @Mapping(target = "productSpecification.id", source = "productSpecificationId")
    @Mapping(target = "productSpecification.name", source = "productSpecificationName")
    @Mapping(target = "productSpecification.version", source = "productSpecificationVersion")
    Product toDomain(ProductJpaEntity entity);

    @Mapping(target = "productOfferingId", source = "productOffering.id")
    @Mapping(target = "productOfferingName", source = "productOffering.name")
    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "productSpecificationName", source = "productSpecification.name")
    @Mapping(target = "productSpecificationVersion", source = "productSpecification.version")
    ProductJpaEntity toEntity(Product domain);

    @Mapping(target = "productOfferingId", source = "productOffering.id")
    @Mapping(target = "productOfferingName", source = "productOffering.name")
    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "productSpecificationName", source = "productSpecification.name")
    @Mapping(target = "productSpecificationVersion", source = "productSpecification.version")
    void updateEntityFromDomain(Product domain, @org.mapstruct.MappingTarget ProductJpaEntity entity);

    @Mapping(target = "price.value", source = "priceValue")
    @Mapping(target = "price.unit", source = "priceUnit")
    Product.ProductPrice mapPriceToDomain(ProductJpaEntity.ProductPriceEmbeddable embeddable);

    @Mapping(target = "priceValue", source = "price.value")
    @Mapping(target = "priceUnit", source = "price.unit")
    ProductJpaEntity.ProductPriceEmbeddable mapPriceToEntity(Product.ProductPrice domain);
}
