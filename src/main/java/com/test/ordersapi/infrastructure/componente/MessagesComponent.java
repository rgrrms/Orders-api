package com.test.ordersapi.infrastructure.componente;

import com.test.ordersapi.infrastructure.configuration.MessagesConfiguration;

public class MessagesComponent {

    private static MessagesConfiguration messages;

    public static void setMessages(MessagesConfiguration messages) {
        MessagesComponent.messages = messages;
    }

    public static String get(String code) {
        return messages.get(code);
    }

    public static String get(String code, String... parameters) {
        return messages.get(code, parameters);
    }
}