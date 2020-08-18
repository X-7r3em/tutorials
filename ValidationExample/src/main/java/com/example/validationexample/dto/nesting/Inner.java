package com.example.validationexample.dto.nesting;

import javax.validation.constraints.NotNull;

public class Inner {
    @NotNull(message = "Inner name can not be null")
    private String innerName;

    public Inner() {
    }

    public Inner(@NotNull String innerName) {
        this.innerName = innerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }
}
