package com.subscriptionengine.productinventory.api.mapper;

import com.subscriptionengine.productinventory.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.ProductStatusType;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "status", expression = "java(mapStatus(dto.getStatus()))")
    Product toDomain(org.openapitools.model.Product dto);

    @Mapping(target = "status", expression = "java(mapStatusType(domain.getStatus()))")
    org.openapitools.model.Product toDto(Product domain);

    default String mapStatus(ProductStatusType type) {
        return type == null ? null : type.getValue();
    }

    default ProductStatusType mapStatusType(String status) {
        return status == null ? null : ProductStatusType.fromValue(status);
    }

    default java.util.List<Product.ProductCharacteristic> mapCharacteristics(java.util.List<org.openapitools.model.Characteristic> dtos) {
        if (dtos == null) return null;
        java.util.List<Product.ProductCharacteristic> list = new java.util.ArrayList<>();
        for (org.openapitools.model.Characteristic dto : dtos) {
            Product.ProductCharacteristic c = new Product.ProductCharacteristic();
            c.setName(dto.getName());
            c.setValueType(dto.getValueType());
            c.setValue(dto.getValue() != null ? dto.getValue().toString() : null);
            list.add(c);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.Characteristic> mapCharacteristicsToDto(java.util.List<Product.ProductCharacteristic> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.Characteristic> list = new java.util.ArrayList<>();
        for (Product.ProductCharacteristic model : models) {
            org.openapitools.model.Characteristic c = new org.openapitools.model.Characteristic();
            c.setName(model.getName());
            c.setValueType(model.getValueType());
            c.setValue(model.getValue());
            list.add(c);
        }
        return list;
    }

    default java.util.List<Product.ProductPrice> mapPrices(java.util.List<org.openapitools.model.ProductPrice> dtos) {
        if (dtos == null) return null;
        java.util.List<Product.ProductPrice> list = new java.util.ArrayList<>();
        for (org.openapitools.model.ProductPrice dto : dtos) {
            Product.ProductPrice p = new Product.ProductPrice();
            p.setName(dto.getName());
            p.setDescription(dto.getDescription());
            p.setPriceType(dto.getPriceType());
            p.setRecurringChargePeriod(dto.getRecurringChargePeriod());
            p.setUnitOfMeasure(dto.getUnitOfMeasure());
            if (dto.getPrice() != null && dto.getPrice().getTaxIncludedAmount() != null) {
                Product.ProductPrice.Price pp = new Product.ProductPrice.Price();
                pp.setValue(dto.getPrice().getTaxIncludedAmount().getValue());
                pp.setUnit(dto.getPrice().getTaxIncludedAmount().getUnit());
                p.setPrice(pp);
            }
            list.add(p);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.ProductPrice> mapPricesToDto(java.util.List<Product.ProductPrice> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.ProductPrice> list = new java.util.ArrayList<>();
        for (Product.ProductPrice model : models) {
            org.openapitools.model.ProductPrice p = new org.openapitools.model.ProductPrice();
            p.setName(model.getName());
            p.setDescription(model.getDescription());
            p.setPriceType(model.getPriceType());
            p.setRecurringChargePeriod(model.getRecurringChargePeriod());
            p.setUnitOfMeasure(model.getUnitOfMeasure());
            if (model.getPrice() != null) {
                org.openapitools.model.Price pp = new org.openapitools.model.Price();
                org.openapitools.model.Money m = new org.openapitools.model.Money();
                m.setValue(model.getPrice().getValue());
                m.setUnit(model.getPrice().getUnit());
                pp.setTaxIncludedAmount(m);
                p.setPrice(pp);
            }
            list.add(p);
        }
        return list;
    }

    default java.util.List<Product.ProductRelationship> mapRelationships(java.util.List<org.openapitools.model.ProductRelationship> dtos) {
        if (dtos == null) return null;
        java.util.List<Product.ProductRelationship> list = new java.util.ArrayList<>();
        for (org.openapitools.model.ProductRelationship dto : dtos) {
            Product.ProductRelationship rel = new Product.ProductRelationship();
            rel.setRelationshipType(dto.getRelationshipType());
            if (dto.getProduct() != null) {
                rel.setProductId(dto.getProduct().getId());
                rel.setProductHref(dto.getProduct().getHref());
                rel.setProductName(dto.getProduct().getName());
            }
            list.add(rel);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.ProductRelationship> mapRelationshipsToDto(java.util.List<Product.ProductRelationship> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.ProductRelationship> list = new java.util.ArrayList<>();
        for (Product.ProductRelationship model : models) {
            org.openapitools.model.ProductRelationship rel = new org.openapitools.model.ProductRelationship();
            rel.setRelationshipType(model.getRelationshipType());
            org.openapitools.model.ProductRefOrValue pref = new org.openapitools.model.ProductRefOrValue();
            pref.setId(model.getProductId());
            pref.setHref(model.getProductHref());
            pref.setName(model.getProductName());
            rel.setProduct(pref);
            list.add(rel);
        }
        return list;
    }

    default java.util.List<Product.PlaceRef> mapPlaces(java.util.List<org.openapitools.model.RelatedPlaceRefOrValue> dtos) {
        if (dtos == null) return null;
        java.util.List<Product.PlaceRef> list = new java.util.ArrayList<>();
        for (org.openapitools.model.RelatedPlaceRefOrValue dto : dtos) {
            Product.PlaceRef p = new Product.PlaceRef();
            p.setId(dto.getId());
            p.setHref(dto.getHref());
            p.setName(dto.getName());
            p.setRole(dto.getRole());
            list.add(p);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.RelatedPlaceRefOrValue> mapPlacesToDto(java.util.List<Product.PlaceRef> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.RelatedPlaceRefOrValue> list = new java.util.ArrayList<>();
        for (Product.PlaceRef model : models) {
            org.openapitools.model.RelatedPlaceRefOrValue p = new org.openapitools.model.RelatedPlaceRefOrValue();
            p.setId(model.getId());
            p.setHref(model.getHref());
            p.setName(model.getName());
            p.setRole(model.getRole());
            list.add(p);
        }
        return list;
    }

    default java.util.List<Product.RelatedPartyRef> mapRelatedParties(java.util.List<org.openapitools.model.RelatedParty> dtos) {
        if (dtos == null) return null;
        java.util.List<Product.RelatedPartyRef> list = new java.util.ArrayList<>();
        for (org.openapitools.model.RelatedParty dto : dtos) {
            Product.RelatedPartyRef rp = new Product.RelatedPartyRef();
            rp.setId(dto.getId());
            rp.setHref(dto.getHref());
            rp.setName(dto.getName());
            rp.setRole(dto.getRole());
            list.add(rp);
        }
        return list;
    }

    default java.util.List<org.openapitools.model.RelatedParty> mapRelatedPartiesToDto(java.util.List<Product.RelatedPartyRef> models) {
        if (models == null) return null;
        java.util.List<org.openapitools.model.RelatedParty> list = new java.util.ArrayList<>();
        for (Product.RelatedPartyRef model : models) {
            org.openapitools.model.RelatedParty rp = new org.openapitools.model.RelatedParty();
            rp.setId(model.getId());
            rp.setHref(model.getHref());
            rp.setName(model.getName());
            rp.setRole(model.getRole());
            list.add(rp);
        }
        return list;
    }
}
