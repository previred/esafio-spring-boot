package cl.previred.task.taskpreviredback.service.impl;

import cl.previred.task.taskpreviredback.domain.User;
import cl.previred.task.taskpreviredback.repository.UserRepository;
import cl.previred.task.taskpreviredback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id, String password) {
        return null;
    }

    @Override
    public Optional<User>  getUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }


}
