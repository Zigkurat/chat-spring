package com.example.chat.service.user.dto;

import java.util.Objects;

public class CreateUserDto {
    private String username;

    private String email;

    private String password;

    private String role;

    public CreateUserDto() {
    }

    public CreateUserDto(final String username, final String email, final String password, final String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserDto)) return false;
        CreateUserDto dto = (CreateUserDto) o;
        return Objects.equals(username, dto.username) &&
                Objects.equals(email, dto.email) &&
                Objects.equals(password, dto.password) &&
                Objects.equals(role, dto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, role);
    }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
