package com.example.chat.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProviderIsMissingEmailException extends RuntimeException{
    private static final long serialVersionUID = 324004362618779844L;

    public ProviderIsMissingEmailException() {
        super("Oauth provider is missing user email");
    }
}
