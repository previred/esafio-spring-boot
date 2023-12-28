package com.nuevospa.task.management.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.task.management.data.entity.UserDao;

public interface IUserRepository extends JpaRepository<UserDao, Long>{	
	
	Optional<UserDao> findByEmail(String email);
	boolean existsByEmail(String email);
	

}
