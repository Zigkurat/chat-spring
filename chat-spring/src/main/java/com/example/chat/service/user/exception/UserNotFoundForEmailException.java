package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundForEmailException extends RuntimeException {
    private static final long serialVersionUID = -768008994017053420L;

    public UserNotFoundForEmailException(final String email) {
        super("User not found for email: " + email);
    }
}
