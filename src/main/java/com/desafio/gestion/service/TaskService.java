package com.desafio.gestion.service;

import com.desafio.gestion.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> findByUserId(Long userId);
}
