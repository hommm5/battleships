package com.example.battle_ships_app.service;
import com.example.battle_ships_app.models.User;
import com.example.battle_ships_app.models.dto.UserDto;
import com.example.battle_ships_app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean register(UserDto userDto){
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            LOGGER.info("Passwords don't match.");
            return false;
        }

        Optional<User> findByEmail = userRepository.findByEmail(userDto.getEmail());

        if (findByEmail.isPresent()){
            LOGGER.info("There is an existing user with this email.");
            return false;
        }


        Optional<User> findByUsername =userRepository.findByUsername(userDto.getUsername());

        if (findByUsername.isPresent()){
            LOGGER.info("There is an existing user with this username.");
            return false;
        }

        User user = modelMapper.map(userDto, User.class);

        this.userRepository.save(user);

        return true;
    }
}
