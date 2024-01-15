package com.challenge.spa.adapter.out.persistence.user;

import com.challenge.spa.application.port.out.user.UserPort;
import com.challenge.spa.common.PersistenceAdapter;
import com.challenge.spa.domain.User;

import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class UserPersistenceAdapter implements UserPort {

  private final SpringUserRepository springUserRepository;

  public UserPersistenceAdapter(SpringUserRepository springUserRepository) {
    this.springUserRepository = springUserRepository;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return springUserRepository
            .findByUsername(username)
            .map(UserEntity::toDomain);
  }

  @Override
  public void save(User user) {
    springUserRepository.save(UserEntity.fromDomain(user));
  }
}
