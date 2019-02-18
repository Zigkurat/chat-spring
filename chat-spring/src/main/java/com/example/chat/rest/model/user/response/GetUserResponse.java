package com.example.chat.rest.model.user.response;

import java.io.Serializable;
import java.util.Objects;

public class GetUserResponse implements Serializable {
    private static final long serialVersionUID = 6741586807726070166L;

    private Long id;

    private String username;

    private String email;

    private String avatarUrl;

    private String role;

    public GetUserResponse() {
    }

    public GetUserResponse(Long id, String username, String email, String avatarUrl, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof GetUserResponse)) return false;
        GetUserResponse that = (GetUserResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, avatarUrl, role);
    }

    @Override
    public String toString() {
        return "GetUserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

