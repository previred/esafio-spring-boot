package co.api.gestiontareas.domain.service;

import co.api.gestiontareas.domain.model.user.User;

public interface AuthService {

    String login(String username, String password) throws Exception;

    User createUser(String username, String password) throws Exception;
}
