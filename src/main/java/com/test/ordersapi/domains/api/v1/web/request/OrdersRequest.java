package com.test.ordersapi.domains.api.v1.web.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {// testar mudanca pra record
    @Size(min = 1, max = 10)
    private List<SingleOrderRequest> ordersRequests;
}
//número controle - número aleatório informado pelo cliente.
//data cadastro (opcional)
//nome - nome do produto
//valor - valor monetário unitário produto
//quantidade (opcional) - quantidade de produtos.
//codigo cliente - identificação numérica do cliente.