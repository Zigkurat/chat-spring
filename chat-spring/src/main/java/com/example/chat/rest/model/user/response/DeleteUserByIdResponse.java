package com.example.chat.rest.model.user.response;

import java.io.Serializable;
import java.util.Objects;

public class DeleteUserByIdResponse implements Serializable {
    private static final long serialVersionUID = -5267068997936273543L;

    private String message;

    public DeleteUserByIdResponse() {
    }

    public DeleteUserByIdResponse(final String message) {
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
        if (!(o instanceof DeleteUserByIdResponse)) return false;
        DeleteUserByIdResponse response = (DeleteUserByIdResponse) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "DeleteUserByIdResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
