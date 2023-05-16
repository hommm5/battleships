package com.example.battle_ships_app.service;

import com.example.battle_ships_app.models.User;
import com.example.battle_ships_app.models.dto.UserRegistrationDto;
import com.example.battle_ships_app.models.dto.UserLoginDto;
import com.example.battle_ships_app.repositories.UserRepository;
import com.example.battle_ships_app.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {
        /*if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            LOGGER.info("Passwords don't match.");
            System.out.println("passss");
            return false;
        }*/

        Optional<User> findByUsername = userRepository.findByUsername(userRegistrationDto.getUsername());

        /*if (findByUsername.isPresent()) {
            LOGGER.info("There is an existing user with this username.");
            return false;
        }*/

        User user = modelMapper.map(userRegistrationDto, User.class);

        this.userRepository.save(user);

        LOGGER.info("A new User has been registered.");

        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {

        Optional<User> user = this.userRepository.
                findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());

        if (user.isEmpty()) {
            return false;
        }


        this.currentUser.login(user.get());


        return true;
    }

    public boolean logout() {
        return true;
    }
}
