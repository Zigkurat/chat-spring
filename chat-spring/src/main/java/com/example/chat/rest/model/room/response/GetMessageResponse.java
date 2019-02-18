package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class GetMessageResponse implements Serializable {
    private static final long serialVersionUID = 2320289169170448744L;

    private Long id;

    private String username;

    private String message;

    public GetMessageResponse() {
    }

    public GetMessageResponse(Long id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(o instanceof GetMessageResponse)) return false;
        GetMessageResponse that = (GetMessageResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, message);
    }

    @Override
    public String toString() {
        return "GetMessageResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
