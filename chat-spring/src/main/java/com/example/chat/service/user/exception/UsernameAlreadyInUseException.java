package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyInUseException extends RuntimeException{
    private static final long serialVersionUID = 7668175304732374178L;

    public UsernameAlreadyInUseException(final String username) {
        super("Username already in use: " + username);
    }

}

