package cl.previred.taskmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.taskmanager.dto.TaskRequest;
import cl.previred.taskmanager.dto.TaskResponse;
import cl.previred.taskmanager.entity.StatusTask;
import cl.previred.taskmanager.entity.Task;
import cl.previred.taskmanager.entity.User;
import cl.previred.taskmanager.exception.StatusNotFoundException;
import cl.previred.taskmanager.exception.TaskNotFoundException;
import cl.previred.taskmanager.exception.UserNotFoundException;
import cl.previred.taskmanager.repository.StatusTaskRepository;
import cl.previred.taskmanager.repository.TaskRepository;
import cl.previred.taskmanager.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StatusTaskRepository statusTaskRepository;

    // Método para obtener todas las tareas
    public List<TaskResponse> getAllTasks() {
        logger.info("Buscando tareas");
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(this::convertToResponse) // Transformar cada Task a TaskResponse
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad Task a TaskResponse
    private TaskResponse convertToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setDescription(task.getDescription());
        response.setDueDate(task.getDueDate());
        
        response.setUserName(task.getUser().getUsername()); // Relación con User
        response.setUserId(task.getUser().getId());

        response.setTaskStatusName(task.getStatusTask().getName()); // Relación con StatusTask
        response.setTaskStatusId(task.getStatusTask().getId());

        return response;
    }
    
    // Método para obtener una tarea por ID
    public TaskResponse getTaskById(Long id) {
        logger.info("Buscando tarea con id: {}", id);

     // Buscar la tarea por ID y convertirla a TaskResponse
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task no encontrada con ID: " + id));

        return convertToResponse(task);
        }
    
    
    // Método para obtener una tarea por ID
    private Task findById(Long id) {
        logger.info("Buscando tarea con id: {}", id);

        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task no encontrada con ID: " + id));
    }

    // Método para crear una nueva tarea
    public TaskResponse createTask(TaskRequest taskRequest) {
        logger.info("Creando una tarea");
        
        Task newTask = new Task();
        newTask.setDescription(taskRequest.getDescription());
        newTask.setDueDate(taskRequest.getDueDate());
        User user = userRepository.findById(taskRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        newTask.setUser(user);
        StatusTask status = statusTaskRepository.findById(taskRequest.getTaskStatusId()).orElseThrow(() -> new StatusNotFoundException("Estado no encontrado"));
        newTask.setStatusTask(status);

        Task task =  taskRepository.save(newTask);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskStatusId(status.getId());
        taskResponse.setTaskStatusName(status.getName());
        
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setUserId(user.getId());
        taskResponse.setUserName(user.getUsername());
        
        return taskResponse;
    }

    // Método para actualizar una tarea
    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        logger.info("Actualizando la tarea con id:{}", id);

        Task existingTask = findById(id);
        
        StatusTask status = statusTaskRepository.findById(updatedTask.getTaskStatusId()).orElseThrow(() -> new StatusNotFoundException("Estado no encontrado"));        
        existingTask.setStatusTask(status);
        
        User user = userRepository.findById(updatedTask.getUserId()).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        existingTask.setUser(user);
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        
        Task task = taskRepository.save(existingTask);
        return convertToResponse(task);
        
    }

    // Método para eliminar una tarea por ID
    public void deleteTask(Long id) {
        logger.info("Borrando la tarea con id:{}", id);
        
        getTaskById(id);
        taskRepository.deleteById(id);
    }
}
