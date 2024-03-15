package com.nuevospa.desafiospringboot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.desafiospringboot.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
