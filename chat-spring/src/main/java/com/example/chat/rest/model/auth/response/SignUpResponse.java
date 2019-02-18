package com.example.chat.rest.model.auth.response;

import java.io.Serializable;
import java.util.Objects;

public class SignUpResponse implements Serializable {
    private static final long serialVersionUID = 196924235765607217L;

    private String message;

    public SignUpResponse() {
    }

    public SignUpResponse(final String message) {
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
        if (!(o instanceof SignUpResponse)) return false;
        SignUpResponse that = (SignUpResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
