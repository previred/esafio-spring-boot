package com.nuevospa.gestion_tareas.model;

public class ApiResponseDetail<T> {
    private String message;
    private T data;
    private boolean success;
    private int status;


    public ApiResponseDetail(String message, T data, boolean success, int status) {
        this.message = message;
        this.data = data;
        this.success = success;
        this.status = status;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
