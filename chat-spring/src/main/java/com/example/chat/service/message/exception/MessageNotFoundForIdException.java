package com.example.chat.service.message.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundForIdException extends RuntimeException{
    private static final long serialVersionUID = 4744366883300068638L;

    public MessageNotFoundForIdException(final Long id) {
        super("Message not found for id: " + id);
    }
}
