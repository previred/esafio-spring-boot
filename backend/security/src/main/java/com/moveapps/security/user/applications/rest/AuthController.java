package com.moveapps.security.user.applications.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import static com.moveapps.security.user.infraestructure.confing.Constants.REQUEST_MAPPING_AUTH;

import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.domains.services.UserService;
import com.moveapps.security.user.infraestructure.adapters.UserAdapter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(REQUEST_MAPPING_AUTH)
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
	UserAdapter userAdapter;
    
    
    @ApiOperation("Register")
    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO user) {
    	return ResponseEntity.ok()
				.body(userAdapter.toResponseBase(userService.saveUser(userAdapter.toEntity(user))));
    }
    
    @ApiOperation("Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
    	return ResponseEntity.ok()
				.body(userAdapter.toResponseBase(userService.login(user)));
    }
    
    @ApiOperation("Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
    	return ResponseEntity.ok()
				.body(userAdapter.toResponseBase(userService.validateToken(token)));
    }
}