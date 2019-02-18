package com.example.chat.service.badword.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadWordNotFoundForIdException extends RuntimeException{
    private static final long serialVersionUID = -5626984493854326017L;

    public BadWordNotFoundForIdException(final Long id) {
        super("Bad word not found for id " + id);
    }
}
