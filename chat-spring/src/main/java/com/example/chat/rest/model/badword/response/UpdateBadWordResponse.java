package com.example.chat.rest.model.badword.response;

import java.io.Serializable;
import java.util.Objects;

public class UpdateBadWordResponse implements Serializable {
    private static final long serialVersionUID = 5046522807133899262L;

    private String message;

    public UpdateBadWordResponse() {
    }

    public UpdateBadWordResponse(final String message) {
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
        if (!(o instanceof UpdateBadWordResponse)) return false;
        UpdateBadWordResponse that = (UpdateBadWordResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "UpdateBadWordResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
