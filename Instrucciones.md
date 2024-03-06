# README

## Instrucciones de Construcción y Ejecución del Proyecto

### 1. Crear el archivo Jar

Para construir el archivo JAR del proyecto, ejecuta el siguiente comando en la línea de comandos:

```
mvn clean install
```

El archivo JAR generado estará en el directorio **_target_** con un nombre similar a demo-[VERSION].jar.

### 2. Despliegue en el Servidor

Para Deplegar el servicio, ejecuta el siguente comando en la línea de comandos:

```
java -jar target/demo-[VERSION].jar
```

### 3. Acceder a la Aplicación

Abre Postman y accede a la siguiente ruta: **_localhost:8585/_**.
Aqui una [Postman Collection](src/main/resources/Nueva SPA collection.postman_collection.json) para importar.

### 4. Endpoints Disponibles

Los endpoints disponibles estan ordenados por carpetas:

1- Login (Post) : utilizado para obtener el Token que se utilizará para los siguientes servicios.

2- Usuarios:
- ObtenerUsuarios (GET): obtiene el listado de usuarios
- CrearUsuario (POST):  para generar un nuevo usuario
- EliminarUsuarios (DELETE):  para eliminar un usuario

3- Tareas:
- ListarTareas (GET):  para listar las tareas
- EncontrarTareaPorID (GET):  para encontrar una tarea por su id
- CrearTarea (POST):  para crear una nueva tarea
- ActualizarTarea (PATCH): para actualizar una tarea existente
- EliminarTarea (DELETE): Eliminar una tarea

4- Roles:
- ListarRoles(GET): obtener lista de roles

5- Estados:
- ListarEstados (GET): obtener lista de estadosTareas

Los endpoints antes mencionados están detallados en el siguente
swagger: [Swagger](http://localhost:8585/swagger-ui/index.html#/)

## Sobre la Aplicacion:

### Base de Datos

Al momento de correr la aplicación, se crea una base de datos en memoria [h2](http://localhost:8080/h2-console), cuya
credenciales son:

```
username=gq
password=12345
```

Las tablas de esta aplicación son:

- **USUARIOS**
  ~~~
  ID_USUARIO(PK)
  NOMBRE
  MAIL
  USERNAME
  PASSWORD
  ID_ROL(FK)
  ~~~

  Tabla que contiene los usuarios de la Aplicación cuya password se guarda encriptada.


- **ESTADOS_TAREAS**
  ~~~
  ID_ESTADO(PK)
  NOMBRE_ESTADO
  ~~~

  Tabla que contiene los estados disponible para las tareas como por ejemplo "**EN EJECUCION**" , "**COMPLETADA**",
  "**CANCELADA**".


- **TAREAS**
  ~~~
  ID(PK)
  NOMBRE_TAREA
  NUMERO_TAREA
  ID_ESTADO(FK)
  ~~~  

  Tabla que contiene la información de las tareas


- **ROLES**
  ~~~
  ID_ROL(PK)
  NOMBRE
  ~~~
  Tabla que contiene la información de los roles.

### Security

La aplicación tiene una capa de seguridad con Spring Security utilizando JWT.
