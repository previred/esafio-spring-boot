package cl.nuevospa.application.port.out;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.nuevospa.domain.h2.entities.TaskDTO;

public interface TaskRepository extends CrudRepository<TaskDTO,Integer> {
    @Override
    List<TaskDTO> findAll();
}
