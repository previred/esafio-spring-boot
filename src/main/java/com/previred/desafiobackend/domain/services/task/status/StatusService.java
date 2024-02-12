package com.previred.desafiobackend.domain.services.task.status;

import com.previred.desafiobackend.data.entities.TaskStatus;
import com.previred.desafiobackend.data.repository.task.status.StatusRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Component
@Log4j2
public class StatusService {

    private final StatusRepository statusRepository;

    private Map<String, TaskStatus> taskStatusMap;

    @PostConstruct
    public void initStatusMap() {
        statusRepository.save(TaskStatus.builder().status("PENDING").build());
        statusRepository.save(TaskStatus.builder().status("DOING").build());
        statusRepository.save(TaskStatus.builder().status("BLOCKED").build());
        statusRepository.save(TaskStatus.builder().status("DONE").build());

        taskStatusMap = new HashMap<>();

        statusRepository.findAll().stream().forEach(status -> {
            taskStatusMap.put(status.getStatus(), status);
        });
    }

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public TaskStatus getStatus(String status) {
        return taskStatusMap.get(status);
    }
}
