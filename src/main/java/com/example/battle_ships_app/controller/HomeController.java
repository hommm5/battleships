package com.example.battle_ships_app.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/home")
    public String LoggedInIndex() {
        return "home";
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        return "index";
    }
}
