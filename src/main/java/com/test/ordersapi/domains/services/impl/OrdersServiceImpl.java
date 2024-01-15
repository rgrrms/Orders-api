package com.test.ordersapi.domains.services.impl;

import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.business.SingleOrderBO;
import com.test.ordersapi.domains.converter.OrdersConverter;
import com.test.ordersapi.domains.entity.Orders;
import com.test.ordersapi.domains.repository.OrdersRepository;
import com.test.ordersapi.domains.services.OrdersService;
import com.test.ordersapi.infrastructure.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final static Integer TEN_PERCENTAGE = 10;
    private final static Integer FIVE_PERCENTAGE = 5;

    @Override
    public OrdersBO createOrders(OrdersBO orderListBO){
        for (SingleOrderBO orderBO: orderListBO.getOrdersListBO()) {
            Optional<Orders> ordersByControlNumber = ordersRepository.findByControlNumber(orderBO.getControlNumber());
            if (ordersByControlNumber.isPresent())
                throw new ConflictException(orderBO.getControlNumber().toString());

            orderBO.setAmount(orderBO.getUnitaryValue().multiply(orderBO.getQuantity()));

            if (orderBO.getQuantity().compareTo(new BigDecimal(TEN_PERCENTAGE)) > 0)
                orderBO.setAmount(formatPercentage(orderBO.getAmount(), TEN_PERCENTAGE));
            else if (orderBO.getQuantity().compareTo(new BigDecimal(FIVE_PERCENTAGE)) > 0)
                orderBO.setAmount(formatPercentage(orderBO.getAmount(), FIVE_PERCENTAGE));

            ordersRepository.save(OrdersConverter.toEntity(orderBO));
        }
        return orderListBO;
    }

    @Override
    public Page<SingleOrderBO> findAllOrders(Long controlNumber, LocalDate date, Pageable page) {
        return ordersRepository.findAll(
                ordersRepository.isEquals("controlNumber", controlNumber)
                        .and(ordersRepository.isEquals("date", date)), page).map(OrdersConverter::toSingleOrderBO);
    }

    private BigDecimal formatPercentage(BigDecimal value, Integer percentage) {
        return value.subtract(value.multiply(new BigDecimal(percentage)).divide(new BigDecimal(100)));
    }
}
