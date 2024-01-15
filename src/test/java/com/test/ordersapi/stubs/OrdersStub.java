package com.test.ordersapi.stubs;

import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.business.SingleOrderBO;
import com.test.ordersapi.domains.entity.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersStub {

    public static Orders getEntity() {
        return Orders.builder()
                .controlNumber(2L)
                .date(LocalDate.parse("2024-01-05"))
                .name("Produto Um")
                .unitaryValue(new BigDecimal(10))
                .quantity(new BigDecimal(5))
                .amount(new BigDecimal(50))
                .customerCode(2L)
                .build();
    }

    public static OrdersBO getBO() {
        return OrdersBO.builder()
                .ordersListBO(List.of(getSingleOrderBO()))
                .build();
    }

    public static String getRequest() {
        return """
                {
                    "ordersRequests": [
                        {
                            "numeroControle": 2,
                            "name": "Produto Um",
                            "date": "2024-01-05",
                            "value": 10,
                            "quantity": 5,
                            "customerCode": 2
                        }
                    ]
                }
                """;
    }

    public static SingleOrderBO getSingleOrderBO() {
        return SingleOrderBO.builder()
                .controlNumber(2L)
                .date(LocalDate.parse("2024-01-05"))
                .name("Produto Um")
                .unitaryValue(new BigDecimal(10))
                .quantity(new BigDecimal(5))
//                .amount(new BigDecimal(50))
                .customerCode(2L)
                .build();
    }

}