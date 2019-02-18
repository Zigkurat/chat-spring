package com.example.chat.rest.model.user.response;

import java.io.Serializable;
import java.util.Objects;

public class CreateUserResponse implements Serializable {
    private static final long serialVersionUID = -4308595695354272086L;

    private String message;

    public CreateUserResponse() {
    }

    public CreateUserResponse(final String message) {
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
        if (!(o instanceof CreateUserResponse)) return false;
        CreateUserResponse response = (CreateUserResponse) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
