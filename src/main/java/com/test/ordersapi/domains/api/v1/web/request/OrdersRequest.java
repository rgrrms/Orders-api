package com.test.ordersapi.domains.api.v1.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {
    @Valid
    @Size(min = 1, max = 10, message = "{order.listOrders}")
    @JacksonXmlProperty(localName = "pedidos")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("pedidos")
    private List<SingleOrderRequest> singleOrderRequest;
}