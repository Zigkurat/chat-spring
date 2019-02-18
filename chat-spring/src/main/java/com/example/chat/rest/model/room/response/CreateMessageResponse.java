package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class CreateMessageResponse implements Serializable {
    private static final long serialVersionUID = 3917507161485874406L;

    private String message;

    public CreateMessageResponse() {
    }

    public CreateMessageResponse(String message) {
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
        if (!(o instanceof CreateMessageResponse)) return false;
        CreateMessageResponse that = (CreateMessageResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CreateMessageResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
