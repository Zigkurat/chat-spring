package com.example.chat.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProviderNotSupportedException extends RuntimeException {
    public ProviderNotSupportedException(String provider) {
        super("Not supported provider: " + provider);
    }
}
