package com.test.ordersapi.domains.api.v1.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SingleOrderResponse {
    @JsonProperty("numeroControle")
    private Long controlNumber;

    @JsonProperty("data")
    private LocalDate date;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("valorUnitario")
    private BigDecimal value;

    @JsonProperty("quantidade")
    private BigDecimal quantity;

    @JsonProperty("valorTotal")
    private BigDecimal amount;

    @JsonProperty("codigoCliente")
    private Long customerCode;
}