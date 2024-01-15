package com.test.ordersapi.domains.business;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class OrdersBO {
    private List<SingleOrderBO> ordersListBO;
}
