package org.example.validationexample.dto.nesting;

import javax.validation.constraints.NotNull;

public class OuterNoValidation {
    @NotNull(message = "Outer name can not be null")
    private String name;

    @NotNull(message = "Inner object can not be null")
    private Inner inner;

    public OuterNoValidation() {
    }

    public OuterNoValidation(@NotNull String name, @NotNull Inner inner) {
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
