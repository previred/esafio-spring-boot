package com.previred.desafiobackend.data.repository.task;

import com.previred.desafiobackend.data.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    Optional<Task> findById(Long taskId);

}
