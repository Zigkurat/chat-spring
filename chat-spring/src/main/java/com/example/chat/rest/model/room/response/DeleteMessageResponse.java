package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class DeleteMessageResponse implements Serializable {
    private static final long serialVersionUID = 3541060043412496423L;

    private String message;

    public DeleteMessageResponse() {
    }

    public DeleteMessageResponse(String message) {
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
        if (!(o instanceof DeleteMessageResponse)) return false;
        DeleteMessageResponse that = (DeleteMessageResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "DeleteMessageResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
