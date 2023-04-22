package com.example.battle_ships_app.service;

import com.example.battle_ships_app.models.User;
import com.example.battle_ships_app.models.dto.UserDto;
import com.example.battle_ships_app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void register(UserDto userDto){
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            throw new RuntimeException("passwords.match");
        }

        Optional<User> findByEmail = userRepository.findByEmail(userDto.getEmail());

        if (findByEmail.isPresent()){
            throw new RuntimeException("email.used");
        }
        User user = modelMapper.map(userDto, User.class);

        this.userRepository.save(user);

    }
}
