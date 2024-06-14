package com.desafio.spring.ec.bs.repository;


import com.desafio.spring.ec.bs.repository.common.GenericRepository;
import com.desafio.spring.ec.ds.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<Users, String> {

    Optional<Users> findByUsername(String username);
}
