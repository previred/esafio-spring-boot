package com.previred.challenge.repositories;

import com.previred.challenge.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {

    Optional<RegisteredUser> findByEmail(String email);

}
