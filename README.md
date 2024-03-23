# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.
<details>
<summary>Especificacions</summary>
  
## Objetivo:
Crear una API RESTful utilizando Spring Boot 2.7.x que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## Requisitos Técnicos:
### Java:
- Utiliza Java 17 para la implementación.
- Utiliza las características de Java 17, como lambdas y streams, cuando sea apropiado.
- Utilizar Maven como gestor de dependencias

### Spring Boot 2.7.x:
- Construye la aplicación utilizando Spring Boot 2.7.x (última versión disponible).

### Base de Datos:

- Utiliza una base de datos H2.
- Crea tres tablas: usuarios, tareas y estados_tarea.
- La tabla usuarios debe contener datos pre cargados.
- La tabla estados_tarea debe contener estados pre cargados.

### JPA:
- Implementa una capa de persistencia utilizando JPA para almacenar y recuperar las tareas.

### JWT (JSON Web Token):

- Implementa la autenticación utilizando JWT para validar usuarios.

### OpenAPI y Swagger:

- Documenta la API utilizando OpenAPI y Swagger.

## Funcionalidades:
### Autenticación:
- Implementa un endpoint para la autenticación de usuarios utilizando JWT. 

### CRUD de Tareas:
- Implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las tareas.

## Consideraciones:
### Seguridad:
- Asegúrate de que las operaciones CRUD de tareas solo sean accesibles para usuarios autenticados.

### Documentación:
- Utiliza OpenAPI y Swagger para documentar claramente la API.
- Puntos adicionales si se genera el API mediante metodologia API First. Generar el archivo openapi.yml Nota: Ejemplo Plugin Maven groupId org.openapitools, artifactId openapi-generator-maven-plugin

### Código Limpio:
- Escribe código ordenado, aplicando buenas prácticas y principios SOLID.

### Creatividad
- Se espera dada la descripción del problema se creen las entidades y metodos en consecuencia a lo solicitado.

## Entregables:
### Repositorio de GitHub:
- Realiza un Pull request a este repositorio indicando tu nombre, correo y cargo al que postulas.
- Todos los PR serán rechazados, no es un indicador de la prueba.

### Documentación:
- Incluye instrucciones claras sobre cómo ejecutar y probar la aplicación.
- **Incluir Json de prueba en un archivo texto o mediante un proyecto postman** Nota: Si no va se restaran puntos de la evaluación

## Evaluación:
Se evaluará la solución en función de los siguientes criterios:

- Correcta implementación de las funcionalidades solicitadas.
- Aplicación de buenas prácticas de desarrollo, patrones de diseño y principios SOLID.
- Uso adecuado de Java 17, Spring Boot 2.7.x, H2, JWT, OpenAPI y Swagger.
- Claridad y completitud de la documentación.
- **Puntos extras si la generación de la API se realizo mediante API First**

</details>

# SOLUCIÓN:
Se utilizó las tecnologías indicadas previamente.
Se dejo una colección de Postman para poder consumir los servicios, se detalla acontinuación la ruta en donde se encuentra la colección y se explica los endpoints que se tienen mapeados.

- Ruta de Postman: **/docs/Moveapps.postman_collection.json**
- Ruta de openapi: **/src/main/resources/openapi.yaml**
- Ruta de Sql initializer: **/src/main/resources/data.sql**
- Puerto por defecto: 9797

- Se utilizaron diversas capas para la solución, así como también se aplico API FIRST, con lo cual los serrvicios parten a partir de la generación de clases desde el openapi.yaml.
- Se utilizó Spring Security para crear al JWT y tener la autorización por medio de un token, los servicios se encuentran securizados.

## Pre-requisitos 📋

* Las versiones son las siguientes: Java 17 y mvn 3.6.3 (pueden usar la versión embebida de IntelliJ)

_Para levantar localmente el servicio se puede realizar de 2 maneras:_

## Levantar Proyecto:

- Primero realizar un realizar un **mvn clean install**.
- Arrancar la aplicación desde el Starter de RetoTecnicoApplication.

#### Con Docker 🔧

* Realizar un git clone al repositorio.
* Abrir el proyecto con el explorador de archivos y abrir la terminal.
* Realizar la siguiente secuencia de comandos:

```
    mvn clean install
    docker build -t moveapps .
    docker run -d -p 8089:8089 -t moveapps:latest moveapps
```

## Explicación de Postman:

- **Authentication(POST): http://localhost:9797/auth/login
  - Se utiliza para la autenticación del usuario registrado en la BD h2:**
```
    REQUEST:
{
    "username": "wpalomino",
    "password": "Elmaestro1$"
}
```
- **Create Task(POST): http://localhost:9797/tasks
  - Permite crear una tarea para el usuario que está logeado:**
```
    REQUEST:
{
    "title": "La caperucita",
    "description": "Historia de una niña en un bosque..."
}
```
- **Get All Task(GET): http://localhost:9797/tasks
  - Permite obtener toda la lista de tareas en el Sistema de Tareas:**


- **Get Task By Id(GET): http://localhost:9797/tasks/{taskId}
  - Permite obtener la tarea por el id:**
```
    Param:
        "taskId": "2"
    Example:
        http://localhost:9797/tasks/3
```
- **Update Task(PUT): http://localhost:9797/tasks/{taskId}
  - Permite actualizar solo la tarea:**
```
    Param:
        "taskId": "2"
    REQUEST:
    {
        "title": "Gook es pto",
        "description": "es la historia de una vaca"
    }
    Example:
        http://localhost:9797/tasks/2
```
- **Tasks of User(GET): http://localhost:9797/taskStatus
  - Permite obtener las Tareas que tiene asignado un Usuario con su respectivo Historial de Estados:**
```
    Obtiene el username del token enviado por el Header Authorization y obtiene las tareas.
```
- **Update Task Status(PUT): http://localhost:9797/taskStatus
  - Permite actualizar y modificar la tarea e estado:**
```
    REQUEST:
   {
        "idTask": 3,
        "status": "EN PROGRESO"
    }
```




