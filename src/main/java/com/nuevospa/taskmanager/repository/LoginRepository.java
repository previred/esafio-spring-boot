package com.nuevospa.taskmanager.repository;

import com.nuevospa.taskmanager.dto.*;

import java.util.Optional;

public interface LoginRepository {

    Optional<UserLogin> findByEmailAndPassword(Login login);
}
