package com.challenge.spa.application.service;

import com.challenge.spa.application.port.in.FindStatusPort;
import com.challenge.spa.application.port.in.ProcessTaskPort;
import com.challenge.spa.application.port.out.statustask.StatusTaskPort;
import com.challenge.spa.application.port.out.task.CrudTaskPort;
import com.challenge.spa.application.port.out.user.UserPort;
import com.challenge.spa.application.shared.Status;
import com.challenge.spa.common.UseCase;
import com.challenge.spa.domain.Task;

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
    return crudTaskPort.load(id).orElse(new Task());
  }

  @Override
  public List<Task> all() {
    return crudTaskPort.all();
  }

  @Override
  public void save(Task task) {
    Status status = (task.getStatusTask() == null) ? Status.CREATED : task.getStatusTask().getStatus();
    task.setStatusTask(findStatusPort.findByStatus(status));
    crudTaskPort.save(task);
  }

  @Override
  public void update(String uuid, Task task) {
    crudTaskPort
        .load(uuid)
        .ifPresent(taskUpdate -> {
          taskUpdate.setNameTask(task.getNameTask());
          taskUpdate.setDescriptionTask(task.getDescriptionTask());
          if(task.getStatusTask() != null)
            taskUpdate.setStatusTask(findStatusPort.findByStatus(task.getStatusTask().getStatus()));
          crudTaskPort.save(taskUpdate);
        });
  }

  @Override
  public void delete(String id) {
    crudTaskPort.delete(id);
  }
}
