package com.challenge.spa.adapter.out.persistence.task;

import com.challenge.spa.application.port.out.task.CrudTaskPort;
import com.challenge.spa.common.PersistenceAdapter;
import com.challenge.spa.domain.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@PersistenceAdapter
public class TaskPersistenceAdapter implements CrudTaskPort {

  private final SpringTaskRepository springTaskRepository;

  public TaskPersistenceAdapter(SpringTaskRepository springTaskRepository) {
    this.springTaskRepository = springTaskRepository;
  }

  @Override
  public void delete(String id) {
    springTaskRepository.deleteById(id);
  }

  @Override
  public Optional<Task> load(String id) {
    return springTaskRepository
            .findById(id)
            .map(TaskEntity::toDomain);
  }

  @Override
  public List<Task> all() {
    return springTaskRepository
            .findAll()
            .stream()
            .map(TaskEntity::toDomain)
            .collect(Collectors.toList());
  }

  @Override
  public Task save(Task task) {
    return springTaskRepository
            .save(TaskEntity.fromDomain(task))
            .toDomain();
  }
}
