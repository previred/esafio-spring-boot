package cl.previred.tasksapi.repository;

import cl.previred.tasksapi.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

    Optional<TaskModel> findByTaskName(String taskName);

    @Modifying
    @Transactional
    @Query("delete from TaskModel t where t.taskName = :taskName")
    void deleteByTaskName(@Param("taskName") String taskName);


}
