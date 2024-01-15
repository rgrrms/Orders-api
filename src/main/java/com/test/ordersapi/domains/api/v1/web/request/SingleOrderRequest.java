package com.test.ordersapi.domains.api.v1.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleOrderRequest {// testar mudanca pra record
    @NotNull
    @JsonProperty("numeroControle")
    @JacksonXmlProperty(isAttribute = true)
    private Long controlNumber;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal value;

    @Builder.Default
    private BigDecimal quantity = BigDecimal.valueOf(1.0);

    @NotNull
    private Long customerCode;
}
//número controle - número aleatório informado pelo cliente.
//data cadastro (opcional)
//nome - nome do produto
//valor - valor monetário unitário produto
//quantidade (opcional) - quantidade de produtos.
//codigo cliente - identificação numérica do cliente.