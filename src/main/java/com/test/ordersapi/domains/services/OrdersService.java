package com.test.ordersapi.domains.services;

import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.business.SingleOrderBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrdersService {

    OrdersBO createOrders(OrdersBO orderListBO);

    Page<SingleOrderBO> findAllOrders(Long controlNumber, LocalDate date, Pageable page);
}
