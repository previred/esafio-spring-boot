package com.previred.taskmanager.controller;

import com.previred.taskmanager.dto.UserDto;
import com.previred.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskManager/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getUserByUserName(@RequestParam String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createNewUser(userDto));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable Long id, @RequestBody String password) {
        return ResponseEntity.ok(userService.updatePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
