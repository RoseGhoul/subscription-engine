package com.subscriptionengine.productcatalog.api.mapper;

import com.subscriptionengine.productcatalog.domain.model.ProductOffering;
import com.subscriptionengine.productcatalog.domain.model.CategoryRef;
import com.subscriptionengine.productcatalog.domain.model.ProductSpecificationRef;
import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPriceRef;
import com.subscriptionengine.productcatalog.domain.model.BundledProductOffering;
import org.mapstruct.Mapper;
import org.openapitools.model.ProductOfferingCreate;
import org.openapitools.model.ProductOfferingUpdate;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ProductOfferingMapper {

    ProductOffering toDomain(ProductOfferingCreate createDto);
    
    ProductOffering toDomain(ProductOfferingUpdate updateDto);

    org.openapitools.model.ProductOffering toDto(ProductOffering domain);

    CategoryRef map(org.openapitools.model.CategoryRef value);
    org.openapitools.model.CategoryRef map(CategoryRef value);

    ProductSpecificationRef map(org.openapitools.model.ProductSpecificationRef value);
    org.openapitools.model.ProductSpecificationRef map(ProductSpecificationRef value);

    ProductOfferingPriceRef map(org.openapitools.model.ProductOfferingPriceRef value);
    org.openapitools.model.ProductOfferingPriceRef map(ProductOfferingPriceRef value);

    BundledProductOffering map(org.openapitools.model.BundledProductOffering value);
    org.openapitools.model.BundledProductOffering map(BundledProductOffering value);
}
