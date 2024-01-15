package com.test.ordersapi.infrastructure.exceptions;

import com.test.ordersapi.infrastructure.componente.MessagesComponent;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException(String parameters) {
        super(MessagesComponent.get("exception.Conflict.Orders", parameters));
    }
}
