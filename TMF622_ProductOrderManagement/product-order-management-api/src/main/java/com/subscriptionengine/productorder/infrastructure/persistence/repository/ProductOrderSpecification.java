package com.subscriptionengine.productorder.infrastructure.persistence.repository;

import com.subscriptionengine.productorder.infrastructure.persistence.entity.ProductOrderJpaEntity;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductOrderSpecification {

    public static Specification<ProductOrderJpaEntity> withFilters(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters != null) {
                filters.forEach((key, value) -> {
                    // Ignore pagination and selection parameters
                    if (!key.equals("limit") && !key.equals("offset") && !key.equals("fields")) {
                        try {
                            // Support exact match on fields
                            predicates.add(criteriaBuilder.equal(root.get(key), value));
                        } catch (IllegalArgumentException e) {
                            // Ignore properties that don't exist on the entity
                        }
                    }
                });
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
