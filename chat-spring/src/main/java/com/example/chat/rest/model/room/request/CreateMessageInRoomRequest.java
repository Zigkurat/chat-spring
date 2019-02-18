package com.example.chat.rest.model.room.request;

import java.io.Serializable;
import java.util.Objects;

public class CreateMessageInRoomRequest implements Serializable {
    private static final long serialVersionUID = 7936921284769176896L;

    private String message;

    public CreateMessageInRoomRequest() {
    }

    public CreateMessageInRoomRequest(String message) {
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
        if (!(o instanceof CreateMessageInRoomRequest)) return false;
        CreateMessageInRoomRequest that = (CreateMessageInRoomRequest) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CreateMessageInRoomRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
