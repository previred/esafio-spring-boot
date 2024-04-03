# Gestión de Tareas con Spring Boot 2.7.x y Java 17

Este proyecto es una API RESTful desarrollada con Spring Boot 2.7.x y Java 17 para la gestión de tareas, incluyendo
autenticación JWT, documentación de API con OpenAPI y Swagger, y persistencia en una base de datos H2.

## Requisitos Previos

Asegúrate de tener los siguientes requisitos previos instalados en tu sistema:

- **Java 17 JDK:** Es necesario tener instalado Java 17 JDK en tu sistema para ejecutar la aplicación.
- **Maven:** Se requiere Maven instalado en tu sistema para compilar y construir el proyecto.
- **Git (opcional):** Si deseas clonar el repositorio, necesitarás tener Git instalado en tu sistema.

## Ejecución del Proyecto

### Compilación y Ejecución

1. Clona el repositorio

2. Navega al directorio del proyecto:

   ```bash
   cd nombre_del_repositorio
   ```

3. Compila el proyecto:

   ```bash
   mvn clean package
   ```

4. Ejecuta las pruebas unitarias antes de ejecutar la aplicación:

   ```bash
   mvn test
   ```

5. Ejecuta la aplicación:

   ```bash
   java -jar target/app-0.0.1-SNAPSHOT.jar
   ```

## Pruebas de la API

### Acceso a la Documentación de la API

Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación de la API a través de Swagger en la
siguiente URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Pruebas con Postman

Para realizar pruebas manuales de la API, se proporciona un archivo de prueba Postman con ejemplos de solicitudes.
Puedes descargar el archivo e importalo a Postman desde el siguiente enlace:

[Desafío Técnico- Gestión de Tareas con Spring Boot y Java 🚀.postman_collection.json](Desafío%20Técnico-%20Gestión%20de%20Tareas%20con%20Spring%20Boot%20y%20Java%20🚀.postman_collection.json)

## Pruebas Unitarias de Integración

El proyecto también incluye pruebas unitarias de integración para garantizar la funcionalidad adecuada de los diferentes
endpoints y casos de uso. Algunas de las pruebas de integración incluidas son:

- Prueba unitaria de integración `signInSuccessIntegrationTest`: Prueba de integración para iniciar sesión con éxito.
- Prueba unitaria de integración `signInWithBlankPasswordAndEmailIntegrationTest`: Prueba de integración para iniciar
  sesión con contraseña y correo electrónico en blanco.
- Prueba unitaria de integración `signInWithBlankPasswordIntegrationTest`: Prueba de integración para iniciar sesión con
  contraseña en blanco.
- Prueba unitaria de integración `signInWithBlankEmailIntegrationTest`: Prueba de integración para iniciar sesión con
  correo electrónico en blanco.
- Prueba unitaria de integración `signInWithInvalidEmailIntegrationTest`: Prueba de integración para iniciar sesión con
  correo electrónico inválido.
- Prueba unitaria de integración `signInUnauthorizedIntegrationTest`: Prueba de integración para iniciar sesión con
  credencial inválido.
- Prueba unitaria de integración `deleteTaskIntegrationTest`: Prueba de integración para eliminar una tarea.
- Prueba unitaria de integración `updateTaskInvalidStatusIntegrationTest`: Prueba de integración para actualizar una
  tarea con un estado inválido.
- Prueba unitaria de integración `updateTaskIntegrationTest`: Prueba de integración para actualizar una tarea.
- Prueba unitaria de integración `createTaskInvalidStatusIntegrationTest`: Prueba de integración para crear una tarea
  con un estado inválido.
- Prueba unitaria de integración `deleteNonExistingTaskIntegrationTest`: Prueba de integración para eliminar una tarea
  que no existe.
- Prueba unitaria de integración `updateNonExistingTaskIntegrationTest`: Prueba de integración para actualizar una tarea
  que no existe.
- Prueba unitaria de integración `getTaskNonExistingIntegrationTest`: Prueba de integración para obtener una tarea que
  no existe.
- Prueba unitaria de integración `getTaskIntegrationTest`: Prueba de integración para obtener una tarea.
- Prueba unitaria de integración `getTasksIntegrationTest`: Prueba de integración para obtener todas las tareas.
- Prueba unitaria de integración `createTaskWithEmptyTitleIntegrationTest`: Prueba de integración para crear una tarea
  con campo requerido en blanco.
- Prueba unitaria de integración `createTaskIntegrationTest`: Prueba de integración para crear una tarea.


