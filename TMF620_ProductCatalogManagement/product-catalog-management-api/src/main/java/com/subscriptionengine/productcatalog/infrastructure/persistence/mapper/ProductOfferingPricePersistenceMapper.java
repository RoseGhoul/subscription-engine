package com.subscriptionengine.productcatalog.infrastructure.persistence.mapper;

import com.subscriptionengine.productcatalog.domain.model.ProductOfferingPrice;
import com.subscriptionengine.productcatalog.infrastructure.persistence.entity.ProductOfferingPriceJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOfferingPricePersistenceMapper {
    
    @Mapping(target = "price.unit", source = "price.priceUnit")
    @Mapping(target = "price.value", source = "price.priceValue")
    @Mapping(target = "unitOfMeasure.amount", source = "unitOfMeasure.uomAmount")
    @Mapping(target = "unitOfMeasure.units", source = "unitOfMeasure.uomUnits")
    ProductOfferingPrice toDomain(ProductOfferingPriceJpaEntity entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "price.priceUnit", source = "price.unit")
    @Mapping(target = "price.priceValue", source = "price.value")
    @Mapping(target = "unitOfMeasure.uomAmount", source = "unitOfMeasure.amount")
    @Mapping(target = "unitOfMeasure.uomUnits", source = "unitOfMeasure.units")
    ProductOfferingPriceJpaEntity toEntity(ProductOfferingPrice domain);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "price.priceUnit", source = "price.unit")
    @Mapping(target = "price.priceValue", source = "price.value")
    @Mapping(target = "unitOfMeasure.uomAmount", source = "unitOfMeasure.amount")
    @Mapping(target = "unitOfMeasure.uomUnits", source = "unitOfMeasure.units")
    void updateEntityFromDomain(ProductOfferingPrice domain, @org.mapstruct.MappingTarget ProductOfferingPriceJpaEntity entity);
}
