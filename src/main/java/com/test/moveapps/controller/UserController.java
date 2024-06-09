package com.test.moveapps.controller;


import com.test.moveapps.domain.dto.UserDto;
import com.test.moveapps.domain.entity.User;
import com.test.moveapps.domain.model.ResponseApi;
import com.test.moveapps.domain.model.TokenResponseApi;
import com.test.moveapps.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "${api.path}")
public class UserController {

    private final MessageSource messageSource;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Operation(summary = "Signup user with the information required")
    @PostMapping(value = "${user.signup.path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseApi<UserDto>> signup(@Valid @RequestBody UserDto request) {
        ResponseApi<UserDto> responseApi = new ResponseApi<>();
        Optional<User> userOptional = this.userService.store(request);

        userOptional.ifPresent(user -> responseApi.setBody(UserDto.convert(user)));
        responseApi.setMessage("Usuario registrado satisfactoriamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApi);
    }

    @Operation(summary="Sign in user with username and password")
    @PostMapping(value = "${user.signin.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseApi> signin(@Valid @RequestBody UserDto request) {
        TokenResponseApi responseApi = new TokenResponseApi();
        Optional<String> tokenOptional = this.userService.authenticate(request.getUsername(), request.getPassword());

        tokenOptional.ifPresent(responseApi::setToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseApi);
    }

}
