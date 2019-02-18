package com.example.chat.rest.model.auth.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class SignInRequest implements Serializable {
    private static final long serialVersionUID = -1188076240721537996L;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(@NotBlank final String email, @NotBlank final String password) {
        this.email = email;
        this.password = password;
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
        if (!(o instanceof SignInRequest)) return false;
        SignInRequest that = (SignInRequest) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "SignInRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
