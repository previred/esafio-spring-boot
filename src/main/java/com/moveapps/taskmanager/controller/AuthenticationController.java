package com.moveapps.taskmanager.controller;

import com.moveapps.taskmanager.common.dto.UserRequestDTO;
import com.moveapps.taskmanager.common.dto.UserResponseDTO;
import com.moveapps.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController extends AbstractController{

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO)  {
        Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.username(), userRequestDTO.password()));

        return ResponseEntity.ok(userService.login(authentication.getName()));
    }

}
