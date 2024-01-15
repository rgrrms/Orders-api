package com.test.ordersapi.domains.repository;

import com.test.ordersapi.domains.entity.Orders;
import com.test.ordersapi.infrastructure.util.SpecificationUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders>, SpecificationUtils<Orders> {
    Optional<Orders> findByControlNumber(Long controlNumber);
}
