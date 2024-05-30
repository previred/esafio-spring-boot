package com.nuevospa.taskmanager.controller;

import io.swagger.annotations.ApiOperation;
import com.nuevospa.taskmanager.dto.User;
import com.nuevospa.taskmanager.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @ApiOperation("save user")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }

}
