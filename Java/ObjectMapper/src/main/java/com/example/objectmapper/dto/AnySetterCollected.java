package com.example.objectmapper.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AnySetterCollected {
    private String infoId;
    // Needs to be initialized for @JsonANySetter
    private Map<String, Object> body = new LinkedHashMap<>();

    public AnySetterCollected() {
    }

    public AnySetterCollected(String infoId, Map<String, Object> body) {
        this.infoId = infoId;
        this.body = body;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    @JsonAnySetter
    public void add(String key, Object value) {
        body.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnySetterCollected that = (AnySetterCollected) o;
        return Objects.equals(infoId, that.infoId) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, body);
    }

    @Override
    public String toString() {
        return "AnySetterCollected{" +
                "infoId='" + infoId + '\'' +
                ", body=" + body +
                '}';
    }
}
