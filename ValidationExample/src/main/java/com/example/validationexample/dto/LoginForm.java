package com.example.validationexample.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @Email
    @NotEmpty(message = "{email.notempty}")
    private String email;

    @NotNull
    private String password;

    public LoginForm() {
    }

    public LoginForm(@NotEmpty(message = "{email.notempty}") @Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}