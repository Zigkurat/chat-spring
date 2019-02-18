package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class CreateRoomResponse implements Serializable {
    private static final long serialVersionUID = 1465566555599902951L;

    private String message;

    public CreateRoomResponse() {
    }

    public CreateRoomResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateRoomResponse)) return false;
        CreateRoomResponse that = (CreateRoomResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CreateRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
