package co.api.gestiontareas.domain.model.user.repository;

import co.api.gestiontareas.domain.model.user.User;

import java.util.List;

public interface UserGateway {
    User save(User users);

    User getUser(String username);

    boolean existByUsername(String username);
}
