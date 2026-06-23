package com.subscriptionengine.productcatalog.infrastructure.persistence.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DynamicSpecificationBuilder<T> {

    public Specification<T> buildSpecification(Map<String, String> filters) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters != null && !filters.isEmpty()) {
                for (Map.Entry<String, String> filter : filters.entrySet()) {
                    String key = filter.getKey();
                    String value = filter.getValue();

                    // Skip standard pagination/fields parameters
                    if (key.equalsIgnoreCase("fields") || key.equalsIgnoreCase("offset") || key.equalsIgnoreCase("limit")) {
                        continue;
                    }

                    // For simplicity, support exact match. 
                    // Could be expanded for like, >, <, etc. based on TMF syntax (e.g. name.regex=...)
                    try {
                        if (root.get(key).getJavaType() == String.class) {
                            if (value.contains("*")) {
                                predicates.add(criteriaBuilder.like(root.get(key), value.replace('*', '%')));
                            } else {
                                predicates.add(criteriaBuilder.equal(root.get(key), value));
                            }
                        } else if (root.get(key).getJavaType() == Boolean.class) {
                            predicates.add(criteriaBuilder.equal(root.get(key), Boolean.parseBoolean(value)));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(key), value));
                        }
                    } catch (IllegalArgumentException e) {
                        // Field does not exist on entity, ignore
                    }
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
