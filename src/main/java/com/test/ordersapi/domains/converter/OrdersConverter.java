package com.test.ordersapi.domains.converter;

import com.test.ordersapi.domains.api.v1.web.request.OrdersRequest;
import com.test.ordersapi.domains.api.v1.web.request.SingleOrderRequest;
import com.test.ordersapi.domains.api.v1.web.response.OrdersResponse;
import com.test.ordersapi.domains.api.v1.web.response.SingleOrderResponse;
import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.business.SingleOrderBO;
import com.test.ordersapi.domains.entity.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrdersConverter {

    public static OrdersBO toOrdersBO(OrdersRequest ordersRequest) {
        return OrdersBO.builder().ordersListBO(ordersRequest.getOrdersRequests().stream()
                .map(OrdersConverter::toSingleOrderBO).collect(Collectors.toList())).build();
    }

    public static SingleOrderBO toSingleOrderBO(SingleOrderRequest singleOrderRequest) {
        return SingleOrderBO.builder()
                .controlNumber(singleOrderRequest.getControlNumber())
                .date(singleOrderRequest.getDate())
                .name(singleOrderRequest.getName())
                .unitaryValue(singleOrderRequest.getValue())
                .quantity(singleOrderRequest.getQuantity())
                .customerCode(singleOrderRequest.getCustomerCode())
                .build();
    }

    public static OrdersBO toOrdersBO(List<Orders> ordersListEntity) {
        return OrdersBO.builder().ordersListBO(ordersListEntity.stream()
                .map(OrdersConverter::toSingleOrderBO).collect(Collectors.toList())).build();
    }

    public static SingleOrderBO toSingleOrderBO(Orders singleOrderEntity) {
        return SingleOrderBO.builder()
                .controlNumber(singleOrderEntity.getControlNumber())
                .date(singleOrderEntity.getDate())
                .name(singleOrderEntity.getName())
                .unitaryValue(singleOrderEntity.getUnitaryValue())
                .quantity(singleOrderEntity.getQuantity())
                .amount(singleOrderEntity.getAmount())
                .customerCode(singleOrderEntity.getCustomerCode())
                .build();
    }

    public static Orders toEntity(SingleOrderBO singleOrderBO) {
        return Orders.builder()
                .controlNumber(singleOrderBO.getControlNumber())
                .date(singleOrderBO.getDate())
                .name(singleOrderBO.getName())
                .unitaryValue(singleOrderBO.getUnitaryValue())
                .quantity(singleOrderBO.getQuantity())
                .amount(singleOrderBO.getAmount())
                .customerCode(singleOrderBO.getCustomerCode())
                .build();
    }

    public static OrdersResponse toOrdersResponse(OrdersBO allOrders) {
        return OrdersResponse.builder().ordersResponses(allOrders.getOrdersListBO().stream()
                .map(OrdersConverter::toSingleOrderResponse).collect(Collectors.toList())).build();
    }

    public static SingleOrderResponse toSingleOrderResponse(SingleOrderBO singleOrderBO) {
        return SingleOrderResponse.builder()
                .controlNumber(singleOrderBO.getControlNumber())
                .date(singleOrderBO.getDate())
                .name(singleOrderBO.getName())
                .value(singleOrderBO.getUnitaryValue())
                .quantity(singleOrderBO.getQuantity())
                .amount(singleOrderBO.getAmount())
                .customerCode(singleOrderBO.getCustomerCode())
                .build();
    }
}
