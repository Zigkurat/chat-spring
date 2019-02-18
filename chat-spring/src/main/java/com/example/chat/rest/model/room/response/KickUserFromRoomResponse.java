package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class KickUserFromRoomResponse implements Serializable {
    private static final long serialVersionUID = -4810986426865692752L;

    private String message;

    public KickUserFromRoomResponse() {
    }

    public KickUserFromRoomResponse(String message) {
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
        if (!(o instanceof KickUserFromRoomResponse)) return false;
        KickUserFromRoomResponse that = (KickUserFromRoomResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "KickUserFromRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
