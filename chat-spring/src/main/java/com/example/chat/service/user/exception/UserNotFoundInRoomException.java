package com.example.chat.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundInRoomException extends RuntimeException{
    private static final long serialVersionUID = 1023527883976478312L;

    public UserNotFoundInRoomException(final String username, final Long roomId) {
        super("User with username: " + username + " not found in room: " + roomId);
    }

    public UserNotFoundInRoomException(final Long userId, final Long roomId) {
        super("User with userId: " + userId + " not found in room: " + roomId);
    }
}
