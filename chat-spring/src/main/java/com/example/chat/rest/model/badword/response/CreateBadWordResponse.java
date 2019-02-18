package com.example.chat.rest.model.badword.response;

import java.io.Serializable;
import java.util.Objects;

public class CreateBadWordResponse implements Serializable {
    private static final long serialVersionUID = 9049176789694521632L;

    private String message;

    public CreateBadWordResponse() {
    }

    public CreateBadWordResponse(final String message) {
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
        if (!(o instanceof CreateBadWordResponse)) return false;
        CreateBadWordResponse that = (CreateBadWordResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CreateBadWordResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
