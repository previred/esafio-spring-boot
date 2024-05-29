package cl.previred.desafio.service;

import cl.previred.desafio.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    UserEntity createUser(UserEntity user);

    UserEntity findUserById(UUID idUser);
}
