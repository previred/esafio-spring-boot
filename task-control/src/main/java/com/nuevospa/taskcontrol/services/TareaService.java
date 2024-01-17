package com.nuevospa.taskcontrol.services;

import com.nuevospa.taskcontrol.dtos.requests.AddTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.DeleteTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.UpdateTareaRequest;
import com.nuevospa.taskcontrol.dtos.responses.TareaResponse;

public interface TareaService {

    TareaResponse addTarea(AddTareaRequest request);
    TareaResponse deleteTarea(DeleteTareaRequest request);
    TareaResponse readTarea(long idTarea);
    TareaResponse updateTarea(UpdateTareaRequest request);
}
