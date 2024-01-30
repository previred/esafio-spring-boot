package com.desafio.spring.service;

import com.desafio.spring.repository.dao.Task;
import com.desafio.spring.repository.dao.TaskStatus;

public interface ITaskStatusService {
    TaskStatus getTaskStatusByName(String name);
}
