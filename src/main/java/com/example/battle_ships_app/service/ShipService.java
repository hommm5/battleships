package com.example.battle_ships_app.service;

import com.example.battle_ships_app.models.Ship;
import com.example.battle_ships_app.models.dto.CreateShipDto;
import com.example.battle_ships_app.models.enums.Name;
import com.example.battle_ships_app.repositories.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;

    @Autowired

    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
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

        Ship ship = modelMapper.map(createShipDto, Ship.class);



        this.shipRepository.save(ship);

        return true;
    }
}
