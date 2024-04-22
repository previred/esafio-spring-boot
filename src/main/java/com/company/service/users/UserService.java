package com.company.service.users;

import com.company.model.LoginRequest;
import com.company.model.TokenResponse;
import com.company.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public void validUserId(UUID uuid);

    public TokenResponse loginUser(LoginRequest loginRequest);
}
