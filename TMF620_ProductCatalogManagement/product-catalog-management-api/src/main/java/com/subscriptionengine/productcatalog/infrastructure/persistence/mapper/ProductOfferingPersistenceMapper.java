package com.subscriptionengine.productcatalog.infrastructure.persistence.mapper;

import com.subscriptionengine.productcatalog.domain.model.*;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOfferingPersistenceMapper {
    
    @Mapping(target = "category", source = "category")
    @Mapping(target = "productSpecification", source = "productSpecification")
    @Mapping(target = "productOfferingPrice", source = "productOfferingPrice")
    @Mapping(target = "bundledProductOffering", source = "bundledProductOffering")
    ProductOffering toDomain(ProductOfferingJpaEntity entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "productSpecification", source = "productSpecification")
    @Mapping(target = "productOfferingPrice", source = "productOfferingPrice")
    @Mapping(target = "bundledProductOffering", source = "bundledProductOffering")
    ProductOfferingJpaEntity toEntity(ProductOffering domain);

    // Mappings for CategoryRef
    @Mapping(target = "id", source = "categoryId")
    @Mapping(target = "href", source = "categoryHref")
    @Mapping(target = "name", source = "categoryName")
    @Mapping(target = "version", source = "categoryVersion")
    CategoryRef mapCategoryRefToDomain(CategoryRefEmbeddable embeddable);

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryHref", source = "href")
    @Mapping(target = "categoryName", source = "name")
    @Mapping(target = "categoryVersion", source = "version")
    CategoryRefEmbeddable mapCategoryRefToEntity(CategoryRef domain);

    // Mappings for ProductSpecificationRef
    @Mapping(target = "id", source = "specId")
    @Mapping(target = "href", source = "specHref")
    @Mapping(target = "name", source = "specName")
    @Mapping(target = "version", source = "specVersion")
    ProductSpecificationRef mapProductSpecificationRefToDomain(ProductSpecificationRefEmbeddable embeddable);

    @Mapping(target = "specId", source = "id")
    @Mapping(target = "specHref", source = "href")
    @Mapping(target = "specName", source = "name")
    @Mapping(target = "specVersion", source = "version")
    ProductSpecificationRefEmbeddable mapProductSpecificationRefToEntity(ProductSpecificationRef domain);

    // Mappings for ProductOfferingPriceRef
    @Mapping(target = "id", source = "priceId")
    @Mapping(target = "href", source = "priceHref")
    @Mapping(target = "name", source = "priceName")
    @Mapping(target = "version", source = "priceVersion")
    ProductOfferingPriceRef mapProductOfferingPriceRefToDomain(ProductOfferingPriceRefEmbeddable embeddable);

    @Mapping(target = "priceId", source = "id")
    @Mapping(target = "priceHref", source = "href")
    @Mapping(target = "priceName", source = "name")
    @Mapping(target = "priceVersion", source = "version")
    ProductOfferingPriceRefEmbeddable mapProductOfferingPriceRefToEntity(ProductOfferingPriceRef domain);

    // Mappings for BundledProductOffering
    @Mapping(target = "id", source = "bundledOfferingId")
    @Mapping(target = "href", source = "bundledOfferingHref")
    @Mapping(target = "name", source = "bundledOfferingName")
    @Mapping(target = "lifecycleStatus", source = "bundledOfferingLifecycleStatus")
    BundledProductOffering mapBundledProductOfferingToDomain(BundledProductOfferingEmbeddable embeddable);

    @Mapping(target = "bundledOfferingId", source = "id")
    @Mapping(target = "bundledOfferingHref", source = "href")
    @Mapping(target = "bundledOfferingName", source = "name")
    @Mapping(target = "bundledOfferingLifecycleStatus", source = "lifecycleStatus")
    BundledProductOfferingEmbeddable mapBundledProductOfferingToEntity(BundledProductOffering domain);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "productSpecification", source = "productSpecification")
    @Mapping(target = "productOfferingPrice", source = "productOfferingPrice")
    @Mapping(target = "bundledProductOffering", source = "bundledProductOffering")
    void updateEntityFromDomain(ProductOffering domain, @org.mapstruct.MappingTarget com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingJpaEntity entity);
}
