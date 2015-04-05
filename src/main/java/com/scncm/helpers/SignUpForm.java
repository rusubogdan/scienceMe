package com.scncm.helpers;

import javax.validation.constraints.NotNull;

public class SignUpForm {

//    @NotNull
    private String email;

//    @NotNull
    private String username;

//    @NotNull
    private String password;

//    @NotNull
    private String confirmedPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
