package com.example.jwtutil.deserialize.model;

import java.util.Map;

public class ParsedToken {
    private Map<String, Object> header;
    private Map<String, Object> payload;
    private boolean isValid;

    public ParsedToken() {
    }

    public ParsedToken(Map<String, Object> header, Map<String, Object> payload, boolean isValid) {
        this.header = header;
        this.payload = payload;
        this.isValid = isValid;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
