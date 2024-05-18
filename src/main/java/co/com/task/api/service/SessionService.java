package co.com.task.api.service;

import co.com.task.api.dto.LoginRequestDTO;
import co.com.task.api.dto.LoginResponseDTO;

public interface SessionService {

    public LoginResponseDTO login(LoginRequestDTO loginRequest);
}
