package com.previred.desafiobackend.data.repository.task.status;

import com.previred.desafiobackend.data.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public interface StatusRepository extends JpaRepository<TaskStatus, Long> {
}
