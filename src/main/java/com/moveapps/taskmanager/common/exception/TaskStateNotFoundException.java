package com.moveapps.taskmanager.common.exception;

public class TaskStateNotFoundException extends RuntimeException{
    public TaskStateNotFoundException(String message){
        super(message);
    }

}
