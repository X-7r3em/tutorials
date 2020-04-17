package com.example.jwtutil.deserialize.model;

public class SerializedToken {
    private String jwt;
    private String secret;

    public SerializedToken() {
    }

    public SerializedToken(String jwt, String secret) {
        this.jwt = jwt;
        this.secret = secret;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
