package com.company.service.users;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import com.company.model.LoginRequest;
import com.company.model.TokenResponse;
import com.company.persistence.users.UserDataProvider;
import com.company.service.users.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{

    private final UserDataProvider userDataProvider;
    private final TokenService tokenService;


    public void  validUserId(UUID uuid) {
        userDataProvider.findByUserId(uuid)
                .orElseThrow(() -> new AppException(CodeExceptions.USER_NOT_FOUND));
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return userDataProvider.findByUserAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                               .map(user -> TokenResponse.builder().token(tokenService.generateToken(user.getEmail())).build())
                               .orElseThrow(() -> new AppException(CodeExceptions.USER_NOT_FOUND));
    }
}
