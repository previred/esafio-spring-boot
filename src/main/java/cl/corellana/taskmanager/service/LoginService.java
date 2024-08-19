package cl.corellana.taskmanager.service;

import cl.corellana.taskmanager.api.model.LoginRequest;
import cl.corellana.taskmanager.api.model.LoginResponse;

public interface LoginService {

    LoginResponse authenticate(LoginRequest  request);
}
