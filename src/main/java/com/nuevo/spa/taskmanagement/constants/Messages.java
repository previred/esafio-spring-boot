package com.nuevo.spa.taskmanagement.constants;

public class Messages {
	 public static final String TAREA_NOT_FOUND = "La tarea con ID %d no fue encontrada.";
	    public static final String TAREA_ELIMINADA = "Tarea eliminada exitosamente.";
	    public static final String TAREA_ACTUALIZADA = "Tarea actualizada exitosamente.";
	    public static final String TAREA_CREADA = "Tarea creada exitosamente.";
	    
	    public static final String BASE_PATH_TAREAS = "/api/tareas";
	    
	    public static final String TAG_TAREAS_NAME = "Gestión de Tareas";
	    public static final String TAG_TAREAS_DESCRIPTION = "API para la gestión de tareas";
	    
	    public static final String GET_ALL_TAREAS_SUMMARY = "Obtener todas las tareas";
	    public static final String GET_TAREA_BY_ID_SUMMARY = "Obtener una tarea por ID";
	    public static final String CREATE_TAREA_SUMMARY = "Crear una nueva tarea";
	    public static final String UPDATE_TAREA_SUMMARY = "Actualizar una tarea existente";
	    public static final String DELETE_TAREA_SUMMARY = "Eliminar una tarea existente";
	    
	    public static final String AUTHENTICATE_PATH = "/authenticate";
	    public static final String INCORRECT_CREDENTIALS = "Usuario o contraseña Incorrecto";
	    
	    public static final String TASK_STATUS_NOT_FOUND = "Estado de tarea no encontrado";
	    public static final String USER_NOT_FOUND = "Usuario no encontrado";
	    
	    public static final long JWT_EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas en milisegundos


	    private Messages() {
	    }
}
