package com.previred.gestion_tareas_api.persistence;

import java.util.Optional;

import com.previred.gestion_tareas_api.entities.User;

public interface UserDAO {

    Optional<User> findByUsername(String name);

     void save(User user);
}
