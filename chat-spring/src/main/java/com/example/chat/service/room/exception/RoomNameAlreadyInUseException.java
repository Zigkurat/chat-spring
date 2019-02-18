package com.example.chat.service.room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoomNameAlreadyInUseException extends RuntimeException{
    private static final long serialVersionUID = 3978432949355772712L;

    public RoomNameAlreadyInUseException(final String name) {
        super("Room name already in use: " + name);
    }
}
