package dev.rhc.apiuser.repository;

import dev.rhc.apiuser.model.Task;
import dev.rhc.apiuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
