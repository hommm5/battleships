package com.example.battle_ships_app.controller;

import com.example.battle_ships_app.models.dto.UserDto;
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



    @Autowired
    public UserController() {

    }

    @ModelAttribute("userDto")
    public UserDto initForm(){
        return new UserDto();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserDto userDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(userDto.toString());

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BidingResult.userRegistrationDTO",
                    bindingResult);
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userDto);
            return "redirect:/register";
        }

        return "redirect:/login";
    }


}
