package com.example.battle_ships_app.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDto {

    @NotBlank(message = "Username should not be empty.")
    @Size(min = 3, max = 10, message = "Length must be between 3 and 10 symbols")
    private String username;

    @NotBlank(message = "Password should not be empty.")
    @Size(min = 3, message = "Length must be at least 3 symbols")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
