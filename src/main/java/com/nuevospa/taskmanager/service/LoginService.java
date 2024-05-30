package com.nuevospa.taskmanager.service;

import com.nuevospa.taskmanager.dto.*;

public interface LoginService {

    TokenResponse login(Login request);
}
