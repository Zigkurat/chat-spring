package com.example.chat.service.user.dto;

import java.util.Objects;

public class UpdateUserDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String role;

    private String avatarUrl;

    public UpdateUserDto() {
    }

    public UpdateUserDto(final Long id, final String username, final String email, final String password, final String role, final String avatarUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUserDto)) return false;
        UpdateUserDto dto = (UpdateUserDto) o;
        return Objects.equals(id, dto.id) &&
                Objects.equals(username, dto.username) &&
                Objects.equals(email, dto.email) &&
                Objects.equals(password, dto.password) &&
                Objects.equals(role, dto.role) &&
                Objects.equals(avatarUrl, dto.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, role, avatarUrl);
    }

    @Override
    public String toString() {
        return "UpdateUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
