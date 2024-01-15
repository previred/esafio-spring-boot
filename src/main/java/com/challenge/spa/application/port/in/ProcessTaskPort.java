package com.challenge.spa.application.port.in;

import com.challenge.spa.domain.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcessTaskPort {
  Task load(String id);
  List<Task> all();
  void save(Task task);
  void update(String id, Task task);
  void delete(String id);
}
