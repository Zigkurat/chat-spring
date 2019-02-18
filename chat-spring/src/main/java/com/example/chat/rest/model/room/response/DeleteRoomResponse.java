package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class DeleteRoomResponse implements Serializable {
    private static final long serialVersionUID = -3482968214855025047L;

    private String message;

    public DeleteRoomResponse() {
    }

    public DeleteRoomResponse(final String message) {
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
        if (!(o instanceof DeleteRoomResponse)) return false;
        DeleteRoomResponse that = (DeleteRoomResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "DeleteRoomResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
