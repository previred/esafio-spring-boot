package com.test.previred.domain.model.user.gateway;

import com.test.previred.domain.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Flux<User> findAll();

    Optional<User> findOneByEmail(String email);

    Mono<User> findById(String id);

    Mono<Void> deleteById(String id);

}
