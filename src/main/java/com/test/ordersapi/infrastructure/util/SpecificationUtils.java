package com.test.ordersapi.infrastructure.util;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public interface SpecificationUtils<T> {

    default Specification<T> isEquals(String fieldName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(value)) return criteriaBuilder.isNotNull(root.get(fieldName));
            else return criteriaBuilder.equal(root.get(fieldName), value);
        };
    }
}
