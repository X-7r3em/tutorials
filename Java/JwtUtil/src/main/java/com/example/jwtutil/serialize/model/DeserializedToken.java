package com.example.jwtutil.serialize.model;

import java.util.Map;

public class DeserializedToken {
    private Map<String, Object> header;
    private Map<String, Object> payload;
    private String secret;

    public DeserializedToken() {
    }

    public DeserializedToken(Map<String, Object> header, Map<String, Object> payload, String secret) {
        this.header = header;
        this.payload = payload;
        this.secret = secret;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
