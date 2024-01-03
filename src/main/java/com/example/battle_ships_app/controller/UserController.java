package com.example.battle_ships_app.controller;

import com.example.battle_ships_app.models.dto.UserRegistrationDto;
import com.example.battle_ships_app.models.dto.UserLoginDto;
import com.example.battle_ships_app.service.UserService;
import com.example.battle_ships_app.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;


    @Autowired
    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @ModelAttribute("userLoginDto")
    public UserLoginDto form() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String login() {
        if (currentUser.getId() != 0) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (currentUser.getId() != 0) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto",
                    bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.login(userLoginDto)) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }


    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }
    @GetMapping("/register")
    public String register() {
        if (currentUser.getId() != 0) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (currentUser.getId() != 0) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto",
                    bindingResult);

            return "redirect:/register";
        }

            userService.register(userRegistrationDto);


        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){

        this.userService.logout();

        return "redirect:/index";
    }


}
