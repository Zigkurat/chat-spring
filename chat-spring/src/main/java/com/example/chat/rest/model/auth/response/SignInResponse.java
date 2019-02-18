package com.example.chat.rest.model.auth.response;

import java.io.Serializable;
import java.util.Objects;

public class SignInResponse implements Serializable {
    private static final long serialVersionUID = -7230835288446227658L;

    private String accessToken;

    private String tokenType = "Bearer";

    public SignInResponse() {
    }

    public SignInResponse(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInResponse)) return false;
        SignInResponse that = (SignInResponse) o;
        return Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(tokenType, that.tokenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, tokenType);
    }

    @Override
    public String toString() {
        return "SignInResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
