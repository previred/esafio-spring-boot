package com.nuevospa.app.repositories;

import com.nuevospa.app.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE  t.deleteAt IS NULL")
    List<Task> findAll();

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.deleteAt IS NULL")
    Optional<Task> findById(@Param("id") Long id);
}