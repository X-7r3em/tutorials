package com.example.jwtutil.common;

public enum Option {
    DESERIALIZE("1"),
    SERIALIZE("2");

    private String code;

    Option(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
