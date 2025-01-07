# gestion-tareas-v1

## Ejecutar Api
- Para iniciar la Api, se debe ejecutar el comando:
```
mvn spring-boot:run
```
- Al iniciar el servicio se ejecutan los archivos schema.sql y data.sql.
  - schema.sql: Contiene los scripts que crean las tablas de la base de datos
  - data.sql: Contiene los scripts que generan los inserts necesarios para que funcione el servicio.
    - Genera 2 roles: DEV (Desarrollador) y SCM (Scrum master)
    - Genera 5 usuarios con claves aleatorias sin encriptar
    - Genera roles para los usuarios, donde sólo el usuario "lfernandez" posee el rol "SCM"


- Operación http://localhost:8080/api/auth/login: Genera un jwt con toda la información del usuario (usuario, password, roles). Este jwt debe utilizarse para consumir cada uno de los servicios de tareas y tablero.


- Cada uno de los servicios de /api/tarea y /api/tablero deben poseer un header de nombre "Authorization", cuyo valor comienza con "Bearer"

  - Ejemplo de Header:
    - Key: Authorization
    - Value: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZmVybmFuZGV6Iiwicm9sZXMiOlsiU0NNIl0sImlhdCI6MTczNjIwMTg3MCwiZXhwIjoxNzM2MjA1NDcwfQ.Z-u7-CXE2s4zlcY5fiZU79zjvbKvJxzPXgIRu-NZktk


- Para crear un tablero, es necesario que el usuario posea el rol "SCM".
  - Para crear una tarea, es necesario que ésta sea asignada a un codigo de proyecto.


- ## Endpoints
En el siguiente link se puede ver en detalle la especificación de la API: https://studio-ws.apicur.io/sharing/b1063fbc-30db-4760-8d67-dbcf70974e01

![Descargar Colección para Postman ](gestion-tareas-v1.postman_collection.json)