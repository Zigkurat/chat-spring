package com.example.chat.rest.model.user.response;

import java.io.Serializable;
import java.util.Objects;

public class UpdateUserResponse implements Serializable {
    private static final long serialVersionUID = -1683270971277825382L;

    private String message;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(final String message) {
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
        if (!(o instanceof UpdateUserResponse)) return false;
        UpdateUserResponse that = (UpdateUserResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "UpdateUserResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
