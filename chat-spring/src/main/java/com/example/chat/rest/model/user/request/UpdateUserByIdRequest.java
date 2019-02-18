package com.example.chat.rest.model.user.request;

import java.io.Serializable;
import java.util.Objects;

public class UpdateUserByIdRequest implements Serializable {
    private static final long serialVersionUID = -2442453819261654383L;

    private String username;

    private String email;

    private String password;

    private String avatarUrl;

    private String role;

    public UpdateUserByIdRequest() {
    }

    public UpdateUserByIdRequest(String username, String email, String password, String avatarUrl, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUserByIdRequest)) return false;
        UpdateUserByIdRequest that = (UpdateUserByIdRequest) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, avatarUrl, role);
    }

    @Override
    public String toString() {
        return "UpdateUserByIdRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
