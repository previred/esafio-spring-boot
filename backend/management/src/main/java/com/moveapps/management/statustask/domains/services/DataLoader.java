package com.moveapps.management.statustask.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;

@Component
public class DataLoader implements ApplicationRunner {

	StatusTaskService statusTaskService;

    @Autowired
    public DataLoader(StatusTaskService statusTaskService) {
        this.statusTaskService = statusTaskService;
    }

    public void run(ApplicationArguments args) {
    	StatusTaskEntity entity = new StatusTaskEntity().builder()
									    			.description("Init")
									    			.build();
    	statusTaskService.save(entity);
    	StatusTaskEntity entity2 = new StatusTaskEntity().builder()
    			.description("Process")
    			.build();
    	statusTaskService.save(entity2);
    	StatusTaskEntity entity3 = new StatusTaskEntity().builder()
    			.description("Pending")
    			.build();
    	statusTaskService.save(entity3);
    	StatusTaskEntity entity4 = new StatusTaskEntity().builder()
    			.description("Finished")
    			.build();
    	statusTaskService.save(entity4);
    	
    }
}