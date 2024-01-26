package com.test.previred.infrastructure.rest.controller.user;

import com.test.previred.domain.model.user.User;
import com.test.previred.domain.usecase.user.UserUseCase;
import com.test.previred.infrastructure.rest.controller.common.Response;
import com.test.previred.infrastructure.rest.controller.user.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping(value = "/register")
    public ResponseEntity<Response<User>> create(@RequestBody UserRequest userRequest) {
        final Response<User> userResponse = new Response<>();
        userResponse.setData(userUseCase.saveUser(userRequest.toUser()));
        userResponse.setMessage(Collections.singletonList("User created successfully"));
        userResponse.setStatus(HttpStatus.CREATED);
        userResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(userResponse.getStatus()).body(userResponse);

    }
}
