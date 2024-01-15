package com.challenge.spa.application.service;

import com.challenge.spa.application.port.in.FindStatusPort;
import com.challenge.spa.application.port.in.ProcessTaskPort;
import com.challenge.spa.application.port.out.statustask.StatusTaskPort;
import com.challenge.spa.application.port.out.task.CrudTaskPort;
import com.challenge.spa.application.port.out.user.UserPort;
import com.challenge.spa.application.shared.Status;
import com.challenge.spa.common.UseCase;
import com.challenge.spa.domain.Task;
import com.challenge.spa.domain.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UseCase
public class ProcessTaskService implements ProcessTaskPort {

  private final CrudTaskPort crudTaskPort;
  private final FindStatusPort findStatusPort;

  public ProcessTaskService(CrudTaskPort crudTaskPort, FindStatusPort findStatusPort) {
    this.crudTaskPort = crudTaskPort;
    this.findStatusPort = findStatusPort;
  }

  @Override
  public Task load(String id) {
    return crudTaskPort
            .load(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found task with id " + id));
  }

  @Override
  public List<Task> all() {
    return crudTaskPort.all();
  }

  @Override
  public void save(Task task) {
    String status = (task.getStatusTask() == null) ? Status.CREATED.name() : task.getStatusTask().getStatus();
    task.setStatusTask(findStatusPort.findByStatus(status));
    crudTaskPort.save(task);
  }

  @Override
  public void update(String uuid, Task task) {
    crudTaskPort
        .load(uuid)
        .ifPresentOrElse(taskUpdate -> {
          taskUpdate.setNameTask(task.getNameTask());
          taskUpdate.setDescriptionTask(task.getDescriptionTask());
          if(task.getStatusTask() != null)
            taskUpdate.setStatusTask(findStatusPort.findByStatus(task.getStatusTask().getStatus()));
          crudTaskPort.save(taskUpdate);
        }, () -> {
          throw new ResourceNotFoundException("Not found task with id " + uuid);
        });
  }

  @Override
  public void delete(String id) {
    crudTaskPort
            .load(id)
            .ifPresentOrElse(taskDelete -> crudTaskPort.delete(id),
                    () -> {
              throw new ResourceNotFoundException("Not found task with id " + id);
            });
  }
}
