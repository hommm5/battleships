package com.example.battle_ships_app.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String username;

    private String password;

    private boolean isLogged;

    public CurrentUser() {
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }
}
