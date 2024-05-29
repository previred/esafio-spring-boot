package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.repository.UserRepository;
import cl.previred.desafio.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public UserEntity findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
