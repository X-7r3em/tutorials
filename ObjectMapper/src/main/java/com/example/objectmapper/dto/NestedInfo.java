package com.example.objectmapper.dto;

import java.util.Map;
import java.util.Objects;

public class NestedInfo {
    private String id;
    private String data;
    private Map<String, Object> body;

    public NestedInfo() {
    }

    public NestedInfo(String id, String data, Map<String, Object> properties) {
        this.id = id;
        this.data = data;
        this.body = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedInfo that = (NestedInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(data, that.data) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, body);
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", body=" + body +
                '}';
    }
}
