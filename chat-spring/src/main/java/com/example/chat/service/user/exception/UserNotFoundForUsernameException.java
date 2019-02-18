package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundForUsernameException extends RuntimeException{
    private static final long serialVersionUID = -4515965468160314476L;

    public UserNotFoundForUsernameException(String username) {
        super("User not found for username: " + username);
    }
}
