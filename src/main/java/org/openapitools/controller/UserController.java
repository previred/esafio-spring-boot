package org.openapitools.controller;

import io.swagger.annotations.ApiOperation;
import org.openapitools.dto.User;
import org.openapitools.dto.UserPaginated;
import org.openapitools.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @ApiOperation("Servicio para guardar un usuario")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("Servicio para para obtener todos los usuarios")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/paginated/{size}/{page}")
    @ApiOperation("Servicio para para obtener todos los usuarios paginados")
    public ResponseEntity<UserPaginated> getAllPaginated(@PathVariable Integer size, @PathVariable Integer page) {
        return ResponseEntity.ok(userService.getAllPaginated(size, page));
    }
}
