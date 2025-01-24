# API REST: Previred task-api App
Esta API REST proporciona servicios relacionados con mantenimiento de registro de Tareas. 
Está desarrollada en **Java 17** utilizando **Spring Boot 3.4.11** con una base de datos en memoria **H2**.

## Requisitos Previos
1. **Java 17**: Asegúrate de tener Java 17 instalado. Verifica con:
   ```bash
   java -version
   ```
2. **Maven**: Herramienta de construcción para Java. Verifica con:
   ```bash
   mvn -version
   ```
3. **Git** (opcional, si clonas el proyecto):
   ```bash
   git --version
   ```

## Compilación y Ejecución
1. **Compilar el Proyecto** con Maven:
   ```bash
   cd tasks-api
   mvn clean install
   ```
2. **Ejecutar** la Aplicación:
   ```bash
   mvn spring-boot:run
   ```
3. **Verificar la Ejecución**: La API estará disponible en:
   > <http://localhost:8086/tasks-api>

## Base de Datos H2
La aplicación utiliza una base de datos en memoria H2. Puedes acceder a la consola de H2 desde:
- URL: http://localhost:8086/h2-console
- Driver: org.h2.Driver
- JDBC URL: jdbc:h2:mem:library
- Usuario: sa
- Contraseña: password

## Servicios REST y Comandos cURL
### 0. Autenticación
### Endpoint:
    POST /tasks-api/api/v1/auth/authenticate

**Descripción: Autenticación para acceso de todos los servicios del CRUD.**
* (El valor del atributo access_token de la respuesta se debe usar en la autorización de los endpoints del CRUD)*

**Comando cURL:**
   ```bash
curl -X POST http://localhost:8086/tasks-api/api/v1/auth/authenticate \
-H "Content-Type: application/json" \
-d '{
    "userName": "admin00",
    "password": "user1234"
}'
   ```

**Respuesta Exitosa:**

   ```json
{
   "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI",
   "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3MzgzMzM2MTN9.TnNEXFH51DEOTwS-itJdBzXKXlFljmyfaUrxuulKYCM"
}
   ```

### 1. Obtener Todos las Tareas existentes
### Endpoint:
    GET /tasks-api/api/v1/tasks

**Descripción**: Devuelve una lista de todos los Tareas disponibles.

**Comando cURL:**
   ```bash
   curl --location 'http://localhost:8086/tasks-api/api/v1/private/tasks' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI'
   ```

**Respuesta Exitosa:**

   ```json
[
   {
      "task_id": 1,
      "task_name": "Analizar código fuente",
      "task_description": "Analizar Cod fuente",
      "task_status_id": -1,
      "task_status_name": "EJECUCION"
   },
   {
      "task_id": 2,
      "task_name": "Analizar código fuente",
      "task_description": "Analizar Cod fuente",
      "task_status_id": -1,
      "task_status_name": "EJECUCION"
   },
   {
      "task_id": 3,
      "task_name": "Analizar código fuente",
      "task_description": "Analizar Cod fuente",
      "task_status_id": -1,
      "task_status_name": "EJECUCION"
   },
   {
      "task_id": 4,
      "task_name": "Analizar código fuente",
      "task_description": "Analizar Cod fuente",
      "task_status_id": -1,
      "task_status_name": "EJECUCION"
   },
   {
      "task_id": 5,
      "task_name": "Desarrollar tests unitarios",
      "task_description": "Analizar Cod fuente",
      "task_status_id": -3,
      "task_status_name": "PENDIENTE"
   }
]
   ```

### 2. Agregar Tarea
### Endpoint:
    POST /tasks-api/api/v1/tasks

**Descripción: Guarda la información de una tarea en la BD.**

**Comando cURL:**
   ```bash
curl --location 'http://localhost:8086/tasks-api/api/v1/private/tasks' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI' \
--data '{
    "task_name": "Tarea01",
    "task_description": "Tarea de prueba",
    "task_status_id": 1
}'
   ```

**Respuesta Exitosa:**

   ```json
{
   "task_id": 1,
   "task_name": "Tarea01",
   "task_description": "Tarea de prueba",
   "task_status_id": 1,
   "task_status_name": "EJECUCION"
}
   ```

### 3. Obtener Tarea por id
### Endpoint:
    GET /tasks-api/api/v1/tasks/1

**Descripción: Devuelve los datos de una tarea a través del id existente.**

**Comando cURL:**
   ```bash
curl --location 'http://localhost:8086/tasks-api/api/v1/private/tasks/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI'
   ```

**Respuesta Exitosa:**

   ```json
{
   "task_id": 1,
   "task_name": "Tarea01",
   "task_description": "Tarea de prueba",
   "task_status_id": 1,
   "task_status_name": "EJECUCION"
}
   ```

### 4. Actualizar Tarea
### Endpoint:
    PUT /tasks-api/api/v1/tasks/1

**Descripción: Actualiza la información de una tarea existente en la BD a través de su Id.**

**Comando cURL:**
   ```bash
curl --location --request PUT 'http://localhost:8086/tasks-api/api/v1/private/tasks/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI' \
--data '{
    "task_name": "Tarea01X",
    "task_description": "Tarea de prueba X",
    "task_status_id": 2
}'
   ```

**Respuesta Exitosa:**

   ```json
{
   "task_id": 1,
   "task_name": "Tarea01X",
   "task_description": "Tarea de prueba X",
   "task_status_id": 2,
   "task_status_name": "PAUSA"
}
   ```

### 5. Eliminar Tarea por id
### Endpoint:
    DELETE /tasks-api/api/v1/tasks/1

**Descripción: Elimina toda la información de una tarea a través del id existente.**

**Comando cURL:**
   ```bash
curl --location --request DELETE 'http://localhost:8086/tasks-api/api/v1/private/tasks/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwIiwiaWF0IjoxNzM3NzI4ODEzLCJleHAiOjE3Mzc4MTUyMTN9.2f3iPrW_9TR0fUE8ZNO1V8kty0rpZ20PxCuwHMC_xoI'
   ```

**Respuesta Exitosa:**

   ```json
true
   ```


## Ejecución de Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando:

   ```bash
   mvn test
   ```

## Despliegue en Producción

1. **Crear el Archivo JAR**:

   ```bash
   mvn clean package
   ```
Esto generará un archivo JAR en la carpeta `target/`.



2. **Ejecutar el JAR**:

   ```bash
   java -jar target/tasks-api-0.0.1-SNAPSHOT.jar
   ```

3. **Verificar el Despliegue**: La API estará disponible en:
   > <http://localhost:8086/tasks-api>

## Posibles Problemas

- **CORS Bloqueado**: Si usas esta API desde un frontend local (como Angular), asegúrate de habilitar CORS en la configuración de Spring Boot.
- **H2 Console No Disponible**: Asegúrate de que la consola H2 esté habilitada en `application.properties`:

   ```properties
  spring.h2.console.enabled=true
  spring.h2.console.path=/h2-console
   ```
