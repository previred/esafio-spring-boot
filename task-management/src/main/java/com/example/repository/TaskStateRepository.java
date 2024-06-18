package com.example.repository;

import com.example.entity.StateTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskStateRepository extends JpaRepository<StateTask, Long> {

    @Query("select st from StateTask st where st.task.taskId = :taskId")
    List<StateTask> findByTaskId(Long taskId);
}
