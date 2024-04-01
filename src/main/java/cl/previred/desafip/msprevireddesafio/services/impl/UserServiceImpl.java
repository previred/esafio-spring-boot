package cl.previred.desafip.msprevireddesafio.services.impl;

import cl.previred.desafip.msprevireddesafio.DTO.LoginRequest;
import cl.previred.desafip.msprevireddesafio.entities.User;
import cl.previred.desafip.msprevireddesafio.repositories.UserRepository;
import cl.previred.desafip.msprevireddesafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean findUser(LoginRequest loginReq) {
        User user = userRepository.findByEmail(loginReq.getUsername());
        return user != null && user.getPassword().equals(loginReq.getPassword());
    }
}
