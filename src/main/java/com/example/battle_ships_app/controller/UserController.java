package com.example.battle_ships_app.controller;

import com.example.battle_ships_app.models.dto.UserDto;
import com.example.battle_ships_app.models.dto.UserLoginDto;
import com.example.battle_ships_app.service.UserService;
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


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userDto")
    public UserDto initForm(){
        return new UserDto();
    }

    @ModelAttribute("userLoginDto")
    public UserLoginDto form(){
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BidingResult.userLoginDto",
                    bindingResult);

            return "redirect:/login";
        }

        if(!this.userService.login(userLoginDto)){
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
        }

        return "redirect:/home";
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

        if(bindingResult.hasErrors() || !this.userService.register(userDto)){
            redirectAttributes.addFlashAttribute("userDto", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BidingResult.userDto",
                    bindingResult);

            return "redirect:/register";
        }

        try{
            userService.register(userDto);
        }catch (RuntimeException s){
            System.out.println(s.getMessage());
            return "redirect:/register";
        }

        return "redirect:/login";
    }



}
