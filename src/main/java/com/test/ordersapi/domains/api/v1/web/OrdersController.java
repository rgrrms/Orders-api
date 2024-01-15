package com.test.ordersapi.domains.api.v1.web;

import com.test.ordersapi.domains.api.v1.web.request.OrdersRequest;
import com.test.ordersapi.domains.api.v1.web.response.OrdersResponse;
import com.test.ordersapi.domains.api.v1.web.response.SingleOrderResponse;
import com.test.ordersapi.domains.converter.OrdersConverter;
import com.test.ordersapi.domains.services.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @GetMapping
    public ResponseEntity<Page<SingleOrderResponse>> searchOrders(@RequestParam(required = false, name = "numeroControle") final Long controlNumber,
                                                                  @RequestParam(required = false, name = "data") final LocalDate date,
                                                                  @PageableDefault final Pageable page) {
        return ResponseEntity.ok(ordersService.findAllOrders(controlNumber, date, page).map(OrdersConverter::toSingleOrderResponse));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrdersResponse> saveOrders(@Valid @RequestBody OrdersRequest ordersRequest) {
        logger.debug("Método saveOrders chamado com: {}", ordersRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ordersService.createOrders(OrdersConverter.toOrdersBO(ordersRequest))).toUri()).build();
    }
}
