package com.subscriptionengine.productinventory.infrastructure.repository.specification;

import com.subscriptionengine.productinventory.infrastructure.entity.ProductJpaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {

    public static Specification<ProductJpaEntity> filterBy(String name, String status, Boolean isBundle) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (StringUtils.hasText(name)) {
                predicates.getExpressions().add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (StringUtils.hasText(status)) {
                predicates.getExpressions().add(cb.equal(root.get("status"), status));
            }

            if (isBundle != null) {
                predicates.getExpressions().add(cb.equal(root.get("isBundle"), isBundle));
            }

            return predicates;
        };
    }
}
