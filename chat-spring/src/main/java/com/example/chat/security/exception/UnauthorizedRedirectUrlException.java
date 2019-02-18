package com.example.chat.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnauthorizedRedirectUrlException extends RuntimeException {
    private static final long serialVersionUID = -6686825484471499132L;

    public UnauthorizedRedirectUrlException(String url) {
        super("Redirected to unauthorized url: " + url);
    }
}
