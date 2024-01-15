package com.test.ordersapi.domains.api.v1.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SingleOrderRequest {
    @NotNull(message = "{order.controlNumber}")
    @JsonProperty("numeroControle")
    private Long controlNumber;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data")
    private LocalDate date = LocalDate.now();

    @NotBlank(message = "{order.name}")
    @Size(min = 1, max = 100, message = "{order.nameMax}")
    @JsonProperty("nome")
    private String name;

    @NotNull(message = "{order.value}")
    @JsonProperty("valor")
    private BigDecimal value;

    @Builder.Default
    @JsonProperty("quantidade")
    private BigDecimal quantity = BigDecimal.valueOf(1.0);

    @NotNull(message = "{order.customerCode}")
    @JsonProperty("codigoCliente")
    private Long customerCode;
}
