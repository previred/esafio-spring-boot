package smarroquin.desafiospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smarroquin.desafiospringboot.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}