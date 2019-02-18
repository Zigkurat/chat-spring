package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class UpdateRoomResponse implements Serializable {
    private static final long serialVersionUID = 8325471904700264699L;

    private String message;

    public UpdateRoomResponse() {
    }

    public UpdateRoomResponse(final String message) {
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
        if (!(o instanceof UpdateRoomResponse)) return false;
        UpdateRoomResponse response = (UpdateRoomResponse) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "UpdateRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
