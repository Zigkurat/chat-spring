package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundForIdException extends RuntimeException {
    private static final long serialVersionUID = 4884808575559976254L;

    public UserNotFoundForIdException(final Long id) {
        super("User not found for Id: " + id);
    }
}
