package com.test.moveapps.service;

import com.test.moveapps.domain.dto.AssignTaskDto;
import com.test.moveapps.domain.dto.AssignTaskUpdateDto;

import java.util.List;

public interface AssignTaskService {

    Boolean assignTaskUser(AssignTaskDto assignTaskDto);

    List<AssignTaskDto> assignTaskList();

    Boolean assignTaskUpdate(AssignTaskUpdateDto assignTaskUpdateDto);

}
