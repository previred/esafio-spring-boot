package com.company.api.login;

import com.company.model.LoginRequest;
import com.company.model.TokenResponse;
import com.company.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController implements  ILoginController{

    private final UserService userService;

    @Override
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest login) {
        return ResponseEntity.ok(userService.loginUser(login));
    }

}
