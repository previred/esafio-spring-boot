package com.test.previred.infrastructure.adapter.h2.user;

import com.test.previred.domain.model.user.User;
import com.test.previred.domain.model.user.gateway.UserRepository;
import com.test.previred.infrastructure.adapter.h2.config.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDataService implements UserRepository {

    private final UserDataRepository userDataRepository;


    @Override
    public User save(User user) {
        user.setPassword(PasswordUtils.hashPassword(user.getPassword()));

        UserEntity userDTO = userDataRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userDTO);
    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findOneByEmail(String email) throws UsernameNotFoundException {
        User user = userDataRepository.findOneByEmail(email).map(UserMapper::toDomain)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));


        return Optional.of(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return null;
    }
}
