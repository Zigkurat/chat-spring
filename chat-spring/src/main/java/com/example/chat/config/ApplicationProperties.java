package com.example.chat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private final Jwt jwt = new Jwt();
    private final Auth auth = new Auth();
    private final OAuth2 outh2 = new OAuth2();


    public static class Jwt {
        private String secret;
        private Long expiration;

        public String getSecret() {
            return secret;
        }

        public void setSecret(final String secret) {
            this.secret = secret;
        }

        public Long getExpiration() {
            return expiration;
        }

        public void setExpiration(final Long expiration) {
            this.expiration = expiration;
        }
    }

    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(final String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(final long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    public static class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public void setAuthorizedRedirectUris(final List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
        }
    }

    public Jwt getJwt() {
        return jwt;
    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOuth2() {
        return outh2;
    }
}
