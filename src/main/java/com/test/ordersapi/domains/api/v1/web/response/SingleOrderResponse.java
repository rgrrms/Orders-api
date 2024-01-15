package com.test.ordersapi.domains.api.v1.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SingleOrderResponse {// testar mudanca pra record
    @JsonProperty("numeroControle")
    private Long controlNumber;

    private LocalDate date;

    private String name;

    private BigDecimal value;

    private BigDecimal quantity;

    private BigDecimal amount;

    private Long customerCode;
}