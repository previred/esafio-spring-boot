package com.nuevospa.desafiospringboot.preload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nuevospa.desafiospringboot.model.TaskStatus;
import com.nuevospa.desafiospringboot.model.User;
import com.nuevospa.desafiospringboot.repository.ITaskStatusRepository;
import com.nuevospa.desafiospringboot.repository.IUserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

	@Autowired
    private PasswordEncoder bcrypt;
    private final IUserRepository userRepository;
    private final ITaskStatusRepository taskStatusRepository;

    public DataInitializer(IUserRepository userRepository, ITaskStatusRepository taskStatusRepository) {
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @PostConstruct
    public void initData() {

    	//Creation user
    	User newUser = new User();
    	newUser.setUsername("felipeA"); 
    	newUser.setPassword(bcrypt.encode("12345678A"));   	
    	userRepository.save(newUser);
    	
    	//Creation taskStatus
    	//id 1
    	TaskStatus newTaskStatus = new TaskStatus();
    	newTaskStatus.setName("Pendiente");    	
        taskStatusRepository.save(newTaskStatus);
        
        //id 2
        TaskStatus newTaskStatusTwo = new TaskStatus();
        newTaskStatusTwo.setName("Completado");    	
        taskStatusRepository.save(newTaskStatusTwo);
    
    }
}
