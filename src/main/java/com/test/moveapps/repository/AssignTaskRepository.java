package com.test.moveapps.repository;

import com.test.moveapps.domain.dto.AssignTaskDto;
import com.test.moveapps.domain.entity.AssignTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long> {

    Optional<AssignTask> findAssignTaskById(Long id);

}
