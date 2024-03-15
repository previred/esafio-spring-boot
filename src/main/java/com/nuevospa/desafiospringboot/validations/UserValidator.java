package com.nuevospa.desafiospringboot.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuevospa.desafiospringboot.exceptions.BadRequestException;
import com.nuevospa.desafiospringboot.exceptions.PaswordNotValidException;
import com.nuevospa.desafiospringboot.exceptions.UserAlreadyExistsException;
import com.nuevospa.desafiospringboot.model.User;
import com.nuevospa.desafiospringboot.repository.IUserRepository;

@Component
public class UserValidator {

    private final IUserRepository userRepository;

    @Autowired
    public UserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(User user) throws BadRequestException {

        StringBuilder message = new StringBuilder();

        if (user.getUsername() == null) {
            message.append("Username must not be null.");
        }

        if (user.getPassword() == null) {
            if (message.length() > 0) {
                message.append(" And ");
            }
            message.append("Password must not be null.");
        }

        if (message.length() > 0) {
            throw new BadRequestException(message.toString());
        }
        
        userRepository.findByUsername(user.getUsername())
        .ifPresent(u -> {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
        });

        if (!PasswordValidator.isValidPassword(user.getPassword())) {
            throw new PaswordNotValidException("The password must be over 8 characters and include at least one uppercase letter.");
        }
    }
}