package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class JoinRoomResponse implements Serializable {
    private static final long serialVersionUID = -8260474530646463807L;

    private String message;

    public JoinRoomResponse() {
    }

    public JoinRoomResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoinRoomResponse)) return false;
        JoinRoomResponse that = (JoinRoomResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "JoinRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
