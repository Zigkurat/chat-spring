package com.example.chat.rest.model.user.request;

import java.io.Serializable;
import java.util.Objects;

public class UpdateCurrentUserRequest implements Serializable {
    private static final long serialVersionUID = 7421384098230099300L;

    private String username;

    private String email;

    private String password;

    private String avatarUrl;

    public UpdateCurrentUserRequest() {
    }

    public UpdateCurrentUserRequest(String username, String email, String password, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateCurrentUserRequest)) return false;
        UpdateCurrentUserRequest that = (UpdateCurrentUserRequest) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(avatarUrl, that.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, avatarUrl);
    }

    @Override
    public String toString() {
        return "UpdateCurrentUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
