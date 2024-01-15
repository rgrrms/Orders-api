package com.test.ordersapi.infrastructure.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final int code;
    private final String objectName;
    private final List<ErrorObject> errors;
}