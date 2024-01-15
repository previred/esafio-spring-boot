package com.challenge.spa.adapter.out.persistence.statustask;

import com.challenge.spa.application.port.out.statustask.StatusTaskPort;
import com.challenge.spa.application.shared.Status;
import com.challenge.spa.common.PersistenceAdapter;
import com.challenge.spa.domain.StatusTask;

import java.util.Optional;

@PersistenceAdapter
public class StatusTaskPersistenceAdapter implements StatusTaskPort {

  private final SpringStatusTaskRepository statusTaskRepository;

  public StatusTaskPersistenceAdapter(SpringStatusTaskRepository statusTaskRepository) {
    this.statusTaskRepository = statusTaskRepository;
  }

  @Override
  public Optional<StatusTask> findByStatus(Status status) {
    return statusTaskRepository
            .findByStatus(status)
            .map(StatusTaskEntity::toDomain);
  }
}
