package com.challenge.spa.application.port.out.task;

import com.challenge.spa.domain.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudTaskPort {
  Optional<Task> load(String id);
  List<Task> all();
  Task save(Task task);
  void delete(String id);
}
