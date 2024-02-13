package cl.nuevospa.taskmanagement.service.impl;

import cl.nuevospa.taskmanagement.converter.TaskConverter;
import cl.nuevospa.taskmanagement.dto.TaskRequestDTO;
import cl.nuevospa.taskmanagement.dto.TaskResponseDTO;
import cl.nuevospa.taskmanagement.entity.Task;
import cl.nuevospa.taskmanagement.entity.TaskState;
import cl.nuevospa.taskmanagement.entity.User;
import cl.nuevospa.taskmanagement.exception.TaskNotFoundException;
import cl.nuevospa.taskmanagement.exception.TaskStateException;
import cl.nuevospa.taskmanagement.exception.UserNotFoundException;
import cl.nuevospa.taskmanagement.repository.TaskRepository;
import cl.nuevospa.taskmanagement.repository.TaskStateRepository;
import cl.nuevospa.taskmanagement.repository.UserRepository;
import cl.nuevospa.taskmanagement.service.TaskService;
import cl.nuevospa.taskmanagement.util.MessagesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStateRepository taskStateRepository;
    private final TaskConverter taskConverter;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskStateRepository taskStateRepository, TaskConverter taskConverter) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskStateRepository = taskStateRepository;
        this.taskConverter = taskConverter;
    }


    @Override
    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) throws Exception {
        // Buscar el usuario por username
        User user = userRepository.findByUsername(taskDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + taskDTO.getUsername()));

        // Buscar el estado de la tarea por nombre
        TaskState taskState = taskStateRepository.findByName(taskDTO.getState())
                .orElseThrow(() -> new TaskStateException("TaskState not found with name: " + taskDTO.getState()));

        // Convertir DTO a entidad y guardar
        Task task = taskConverter.convertToEntity(taskDTO, user, taskState);
        task = taskRepository.save(task);

        // Convertir entidad guardada a DTO para la respuesta
        return taskConverter.convertToDTO(task);
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTask(UUID taskId, TaskRequestDTO taskDTO) {
        // Encuentra la tarea existente por su ID.
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(MessagesConstants.TASK_NOT_FOUND + " " + taskId));

        // Busca el usuario por username.
        User user = userRepository.findByUsername(taskDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + taskDTO.getUsername()));

        // Busca el estado de la tarea por nombre.
        TaskState taskState = taskStateRepository.findByName(taskDTO.getState())
                .orElseThrow(() -> new TaskStateException("TaskState not found with name: " + taskDTO.getState()));

        // Actualiza la tarea utilizando el patrón builder.
        task = Task.builder()
                .id(task.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .user(user)
                .taskState(taskState)
                .build();

        // Guarda la tarea actualizada en la base de datos.
        Task updatedTask = taskRepository.save(task);

        // Convierte la tarea actualizada a un DTO para la respuesta.
        return taskConverter.convertToDTO(updatedTask);
    }



    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        // Encuentra la tarea existente por su ID.
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(MessagesConstants.TASK_NOT_FOUND + " " + taskId));

        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskConverter.convertToDTOList(tasks);
    }

    @Override
    public TaskResponseDTO getTaskById(UUID taskId) {
        // Encuentra la tarea existente por su ID.
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(MessagesConstants.TASK_NOT_FOUND + " " + taskId));

        return taskConverter.convertToDTO(task);
    }
}
