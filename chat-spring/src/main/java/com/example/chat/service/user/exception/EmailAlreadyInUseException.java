package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends RuntimeException{
    private static final long serialVersionUID = -7878915293822078890L;

    public EmailAlreadyInUseException(final String email) {
        super("Email already in use: " + email);
    }

}
