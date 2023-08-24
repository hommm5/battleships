package com.example.battle_ships_app.service;

import com.example.battle_ships_app.models.Category;
import com.example.battle_ships_app.models.Ship;
import com.example.battle_ships_app.models.User;
import com.example.battle_ships_app.models.dto.CreateShipDto;
import com.example.battle_ships_app.models.enums.Name;
import com.example.battle_ships_app.repositories.CategoryRepository;
import com.example.battle_ships_app.repositories.ShipRepository;
import com.example.battle_ships_app.repositories.UserRepository;
import com.example.battle_ships_app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private CurrentUser currentUser;
    private final UserRepository userRepository;

    @Autowired

    public ShipService(ShipRepository shipRepository,
                       ModelMapper modelMapper,
                       CategoryRepository categoryRepository,
                       CurrentUser currentUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDto createShipDto) {

        Optional<Ship> byName = this.shipRepository.findByName(createShipDto.getName());

        if (byName.isPresent()){
            return false;
        }

        Name shipType = switch (createShipDto.getCategory()){
            case 0 -> Name.BATTLE;
            case 1 -> Name.CARGO;
            case 2 -> Name.PATROL;
            default -> Name.BATTLE;
        };
        Optional<User> owner = userRepository.findById(currentUser.getId());

        Category categoryByName = this.categoryRepository.findByName(shipType);

        Ship ship = modelMapper.map(createShipDto, Ship.class);

        ship.setCategory(categoryByName);
        ship.setUser(owner.get());

        this.shipRepository.save(ship);

        return true;
    }
}
