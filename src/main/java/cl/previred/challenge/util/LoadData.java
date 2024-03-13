package cl.previred.challenge.util;

import cl.previred.challenge.model.TaskStatus;
import cl.previred.challenge.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

  @Autowired
  private TaskStatusRepository taskStatusRepository;

  @Override
  public void run(String... args) {
    if (taskStatusRepository.count() == 0) {
      taskStatusRepository.save(new TaskStatus(1L, "Pendiente"));
      taskStatusRepository.save(new TaskStatus(2L, "En Progreso"));
      taskStatusRepository.save(new TaskStatus(3L, "Completada"));
    }
  }

}
