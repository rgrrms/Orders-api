package com.test.ordersapi.stubs;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationStub<T> {
    public Specification<T> equal() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fieldName"), "value");
    }
}
