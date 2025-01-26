package cl.previred.task.taskpreviredback.service;

import cl.previred.task.taskpreviredback.domain.User;

import java.util.Optional;

public interface UserService {

    User getUser(Long id, String password);

    Optional<User> getUserByUserName(String username);

    void saveUser(User user);
}
