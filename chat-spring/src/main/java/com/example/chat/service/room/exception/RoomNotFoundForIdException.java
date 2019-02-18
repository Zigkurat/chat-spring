package com.example.chat.service.room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundForIdException extends RuntimeException {
    private static final long serialVersionUID = 2814066290854955876L;

    public RoomNotFoundForIdException(final Long id) {
        super("Room not found for id: " + id);
    }
}
