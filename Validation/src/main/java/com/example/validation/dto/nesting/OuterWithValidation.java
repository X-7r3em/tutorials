package com.example.validation.dto.nesting;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OuterWithValidation {
    @NotNull(message = "Outer name can not be null")
    private String name;

    @Valid
    @NotNull(message = "Inner object can not be null")
    private Inner inner;

    public OuterWithValidation() {
    }

    public OuterWithValidation(@NotNull String name, @NotNull Inner inner) {
        this.name = name;
        this.inner = inner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }
}
