package com.test.ordersapi.domains.business;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SingleOrderBO {
    private Long controlNumber;
    private LocalDate date;
    private String name;
    private BigDecimal unitaryValue;
    private BigDecimal quantity;
    private BigDecimal amount;
    private Long customerCode;
}
