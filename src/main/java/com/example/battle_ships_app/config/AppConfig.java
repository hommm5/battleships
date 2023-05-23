package com.example.battle_ships_app.config;

import com.example.battle_ships_app.models.Ship;
import com.example.battle_ships_app.models.dto.CreateShipDto;
import com.example.battle_ships_app.models.enums.Name;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }
}
