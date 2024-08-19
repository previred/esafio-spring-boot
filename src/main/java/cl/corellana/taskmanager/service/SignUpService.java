package cl.corellana.taskmanager.service;

import cl.corellana.taskmanager.api.model.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest request);
}
