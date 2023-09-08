package com.example.battle_ships_app.controller;

import com.example.battle_ships_app.models.dto.CreateShipDto;
import com.example.battle_ships_app.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @ModelAttribute("createShipDto")
    public CreateShipDto form() {
        return new CreateShipDto();
    }

    @GetMapping("/ships/add")
    public String ships() {
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDto createShipDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.shipService.create(createShipDto)) {
            redirectAttributes.addFlashAttribute("createShipDto", createShipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDto",
                    bindingResult);
            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
