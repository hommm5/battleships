package com.example.battle_ships_app.controller;

import com.example.battle_ships_app.models.dto.ShipDto;
import com.example.battle_ships_app.models.dto.StartBattleDto;
import com.example.battle_ships_app.service.ShipService;
import com.example.battle_ships_app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final CurrentUser currentUser;

    @Autowired
    public HomeController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("startBattleDto")
    public StartBattleDto initFrom() {
        return new StartBattleDto();
    }

    @GetMapping("/")
    public String LoggedInIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String loggedOutIndex(Model model) {
        if (currentUser.getId() == null) {
            return "redirect:/";
        }
        long loggedUserId = currentUser.getId();
        List<ShipDto> ownShips = this.shipService.getOwnShips(loggedUserId);
        List<ShipDto> enemyShips = this.shipService.getEnemyShips(loggedUserId);
        List<ShipDto> sortedShips = this.shipService.getAllSortedShips();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }


}
