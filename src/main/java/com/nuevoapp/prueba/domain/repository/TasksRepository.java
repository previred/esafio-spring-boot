package com.nuevoapp.prueba.domain.repository;

import com.nuevoapp.prueba.domain.model.entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<TasksEntity, Integer> {
    List<TasksEntity> findAllByUserEmail(String email);
    void deleteById(Integer id);

}
