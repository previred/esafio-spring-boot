# Endpoints de la aplicación

## Autenticación de usuario

Este endpoint se utiliza para autenticar a un usuario y generar un token de acceso.

- **URL:** `POST http://localhost:8080/login`
- **Payload de entrada:**
  ```json
  {
    "username": "USUARIO1",
    "password": "123456"
  }

##Respuesta exitosa:
Código de estado: 200 OK

Cuerpo de respuesta: String que representa el token de acceso generado.

##Gestión de tareas
Estos endpoints se utilizan para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las tareas.

##Crear una nueva tarea
- **URL**: `POST http://localhost:8080/api/tasks`
Encabezado:
Content-Type: application/json
Authorization: Bearer {token}
(Reemplaza {token} con el token de acceso JWT generado en la autenticación)
Payload de entrada:
{
"title": "Tarea de prueba",
"description": "Esta es una tarea de prueba",
"status": "Pendiente",
"userCreator": "usuario_creador",
"userAssigned": "usuario_asignado"
}
##Respuesta exitosa:
Código de estado: 201 Created
Cuerpo de respuesta: Objeto Task que representa la tarea creada.
Obtener todas las tareas
URL: GET http://localhost:8080/api/tasks
Encabezado:
Authorization: Bearer {token}
(Reemplaza {token} con el token de acceso JWT generado en la autenticación)
Respuesta exitosa:
Código de estado: 200 OK
Cuerpo de respuesta: Lista de objetos Task que representan todas las tareas.
##Actualizar una tarea existente
URL: PUT http://localhost:8080/api/tasks
Encabezado:
Content-Type: application/json
Authorization: Bearer {token}
(Reemplaza {token} con el token de acceso JWT generado en la autenticación)
Payload de entrada:
json
Copy code
{
"id": 1,
"title": "Tarea actualizada",
"description": "Descripción actualizada",
"status": "En progreso",
"userCreator": "usuario_creador",
"userAssigned": "usuario_asignado"
}
Respuesta exitosa:
Código de estado: 200 OK
Cuerpo de respuesta: Objeto Task que representa la tarea actualizada.
##Eliminar una tarea
URL: DELETE http://localhost:8080/api/tasks/{id}
(Reemplaza {id} con el ID de la tarea que deseas eliminar)
Encabezado:
Authorization: Bearer {token}
(Reemplaza {token} con el token de acceso JWT generado en la autenticación)
Respuesta exitosa:
Código de estado: 204 No Content