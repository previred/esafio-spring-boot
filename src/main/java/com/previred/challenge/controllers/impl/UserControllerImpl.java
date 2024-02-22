package com.previred.challenge.controllers.impl;

import com.previred.challenge.controllers.UserController;
import com.previred.challenge.dto.JwtResponseDTO;
import com.previred.challenge.dto.UserLoginRequestDTO;
import com.previred.challenge.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @NonNull
    private final UserService userService;

    public JwtResponseDTO authenticate(UserLoginRequestDTO userLoginRequestDTO) {
        log.info("Authenticating user {}", userLoginRequestDTO.email());
        String token = userService.authenticate(userLoginRequestDTO);
        return new JwtResponseDTO(token);
    }

}
