package com.example.battle_ships_app.repositories;

import com.example.battle_ships_app.models.Ship;
import com.example.battle_ships_app.models.dto.CreateShipDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);
}
