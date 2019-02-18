package com.example.chat.rest.model.auth.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class SignUpRequest implements Serializable {
    private static final long serialVersionUID = 2973751186148880810L;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank final String username, @Email @NotBlank final String email, @NotBlank final String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignUpRequest)) return false;
        SignUpRequest that = (SignUpRequest) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
