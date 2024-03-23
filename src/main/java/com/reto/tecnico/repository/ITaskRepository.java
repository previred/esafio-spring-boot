package com.reto.tecnico.repository;

import com.reto.tecnico.model.Tasks;
import com.reto.tecnico.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends JpaRepository<Tasks, Long> {
  Optional<Tasks> findByTaskId(Long id);
  Optional<Tasks> findByTitle(String title);
  List<Tasks> findAllByUser(User user);

}
