    package com.spa.task.service;

    import com.spa.task.entity.Task;
    import com.spa.task.entity.User;
    import com.spa.task.exception.NotFoundException;
    import com.spa.task.repository.TaskRepository;
    import com.spa.task.repository.UserRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    @AllArgsConstructor
    public class TaskServiceImpl implements TaskService{

        private final TaskRepository taskRepository;
        private final UserRepository userRepository;

        @Override
        public List<Task> findAll() {
            return taskRepository.findAll();
        }

        @Override
        public Task findById(Long id) {
            return taskRepository.findById(id).orElseThrow();
        }

        @Override
        public Task create(Task obj) {

            obj.setUser(User.builder().id(getUserId()).build());

            return taskRepository.save(obj);
        }

        @Override
        public Task update(Task obj, Long id) {

            Optional<Task> taskFound = taskRepository.findById(id);
            if (taskFound.isEmpty()) {
                throw new NotFoundException("E400","No se encontró el elemento con ID: " + id);
            }

            taskFound.get().setTask(obj.getTask());
            taskFound.get().setStatus(obj.getStatus());
            taskFound.get().setUser(obj.getUser().getUsername() == null ? User.builder().id(getUserId()).build()
                    : User.builder().id(getUserId(obj.getUser().getUsername())).build());

            return taskRepository.save(taskFound.get());

        }

        @Override
        public void delete(Long id) {

            Optional<Task> taskFound = taskRepository.findById(id);
            if (taskFound.isEmpty()) {
                throw new NotFoundException("E400","No se encontró el elemento con ID: " + id);
            }

            taskRepository.deleteById(id);

        }

        public Integer getUserId() {

            String userName = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                    .map(authentication -> authentication.getName()).orElseThrow();

            return getUserId(userName);

        }

        public Integer getUserId(String userName) {

            Optional<User> userFound = userRepository.findByUsername(userName);
            if (userFound.isEmpty()) {
                throw new NotFoundException("E400","No se encontró user con name: " + userName);
            }

            return userFound.get().getId();

        }

    }
