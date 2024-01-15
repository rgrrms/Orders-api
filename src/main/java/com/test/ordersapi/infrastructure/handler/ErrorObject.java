package com.test.ordersapi.infrastructure.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErrorObject {

    private final String message;
    private final String field;
    private final Object parameter;
}