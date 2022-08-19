package com.example.nikson.payload;

import javax.validation.constraints.Email;

public class UserLoginRequest {

    private String email;
    private String password;

    @Email
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
