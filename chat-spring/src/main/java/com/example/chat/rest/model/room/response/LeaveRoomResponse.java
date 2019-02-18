package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class LeaveRoomResponse implements Serializable {
    private static final long serialVersionUID = 7142997076364246556L;

    private String message;

    public LeaveRoomResponse() {
    }

    public LeaveRoomResponse(String message) {
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
        if (!(o instanceof LeaveRoomResponse)) return false;
        LeaveRoomResponse that = (LeaveRoomResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "LeaveRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
