package cl.previred.desafio.service;

import java.util.List;
import cl.previred.desafio.dto.TareasDto;
import cl.previred.desafio.model.Tareas;

public interface TareasService {

    public String saveTask(List<Tareas> tasks) throws Exception;

    public String updateTask(Tareas task) throws Exception;

    public List<TareasDto> getAllTasks() throws Exception;

    public TareasDto findTaskById(int id) throws Exception;

    public void deleteTaskById(int id) throws Exception;
}
