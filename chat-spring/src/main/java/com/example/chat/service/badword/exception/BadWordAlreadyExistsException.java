package com.example.chat.service.badword.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadWordAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -8543646646422185055L;

    public BadWordAlreadyExistsException(final String text) {
        super("Bad word " + text + " already exists.");
    }
}
