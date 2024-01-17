package cl.previred.challenge.repository;

import cl.previred.challenge.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByCreatedBy_Email(String email);
    List<Task> findByCreatedBy_Id(Integer id);
}
