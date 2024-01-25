
# Desafio Spring Boot

Servicio realizado utilizando la arquitectura de microservicios en Spring 2.7 para desafio enero 2024.

## Dependencias

Las dependencias y tecnologia utilizada fueron

```bash
* lombok
* database h2
* jsonwebtoken, security
* jpa
* open api
* bean validation
```
Se dejaron habilitados los endpoints de acceso al:
* swagger: http://localhost:8080/swagger-ui/index.html#/
* h2-console: http://localhost:8080/h2-console/login.jsp
## Instrucciones

El proyecto fue realizado en Java 17, automaticamente ejecutado el proyecto creara las tres tablas(tasks,task_status,user)

`El usuario agregado para pruebas fue 'admin' con password 'admin'`

`se agregaron cuatro estados a la tabla TASK_STATUS`

| ID | NAME     | STATE                       |
| :-------- | :------- | :-------------------------------- |
| `1`      | `TO_DO` | true |
| `2`      | `IN_PROGRESS` | true |
| `3`      | `TO_VERIFY` | true |
| `4`      | `DONE` | true |

`solo se creo una tarea de prueba con id=1 por defecto`


## API Reference Auth

#### Post register

- Endpoint de registro de usuario

```http
  POST /auth/register
```
```bash
{
    "username":"username-test",
    "password":"password-test",
    "firstname":"nombre-test",
    "lastname":"lastname-test",
    "country":"PE"
}
```
#### Post login

- Endpoint de obtencion de token(logueo)
```http
  POST /auth/login
```
```bash
{
    "username":"admin",
    "password":"admin"
}
```

## API Reference Task

#### Get all tasks

- Obtener toda la lista de tareas creadas.

```http
  GET /v1/task
```
#### Get task by id

- Busqueda de tarea  por Id

```http
  GET /v1/task/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id de la tarea |

#### Post task

- Crear tarea

```http
  POST /v1/task
```
```bash
{
    "task": "realizarQuery",
    "statusId": 1
}
```
se crea la tarea con el estado(statusId) en TO_DO, el endpoint asigna automaticamente al usuario logueado al igual que los campos de auditoria de la tabla.

#### Update task

- Actualizar tarea

```http
  PUT /v1/task/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id de la tarea |

* actualizar tarea y status de la tarea.

```bash
{
    "task": "realizarQuery",
    "statusId": 1
}
```
* actualizar tarea, status de la tarea y se le asigna a otro usuario la tarea.

```bash
{
    "task": "modelando_BD",
    "statusId": 3,
    "user":"joaquin"
}
```
#### Delete task by id

- Eliminar tarea por Id

```http
  DELETE /v1/task/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id de la tarea |


