package com.example.chat.rest.model.badword.response;

import java.io.Serializable;
import java.util.Objects;

public class DeleteBadWordResponse implements Serializable {
    private static final long serialVersionUID = 6342698330856792072L;

    private String message;

    public DeleteBadWordResponse() {
    }

    public DeleteBadWordResponse(final String message) {
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
        if (!(o instanceof DeleteBadWordResponse)) return false;
        DeleteBadWordResponse that = (DeleteBadWordResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "DeleteBadWordResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
