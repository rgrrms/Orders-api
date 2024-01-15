package com.test.ordersapi.domains.api.v1.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrdersResponse {
    private List<SingleOrderResponse> ordersResponses;
}
