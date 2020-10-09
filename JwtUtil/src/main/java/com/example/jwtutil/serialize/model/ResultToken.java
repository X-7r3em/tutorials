package com.example.jwtutil.serialize.model;

public class ResultToken {
    private String jwt;
    private String secret;

    public ResultToken() {
    }

    public ResultToken(String jwt, String secret) {
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
