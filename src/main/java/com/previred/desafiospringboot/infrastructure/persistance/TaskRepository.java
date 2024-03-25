package com.previred.desafiospringboot.infrastructure.persistance;

import com.previred.desafiospringboot.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAll();
    Optional<Task> findById(Integer id);
    List<Task> findByStatus(String status);
    List<Task> findByUserCreator(String user);
    List<Task> findByUserAssigned(String user);
}
