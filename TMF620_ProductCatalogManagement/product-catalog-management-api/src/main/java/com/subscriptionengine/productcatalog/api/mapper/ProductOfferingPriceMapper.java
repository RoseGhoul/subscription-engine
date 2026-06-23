package com.subscriptionengine.productcatalog.api.mapper;

import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;
import org.mapstruct.Mapper;
import org.openapitools.model.ProductOfferingPriceCreate;
import org.openapitools.model.ProductOfferingPriceUpdate;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ProductOfferingPriceMapper {

    ProductOfferingPrice toDomain(ProductOfferingPriceCreate createDto);
    
    ProductOfferingPrice toDomain(ProductOfferingPriceUpdate updateDto);

    org.openapitools.model.ProductOfferingPrice toDto(ProductOfferingPrice domain);
}
