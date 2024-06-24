# Gestión de Tareas - Nuevo SPA

## Requisitos Previos

- Java: JDK 17
- Maven: 3.6.0 o superior
- IDE: Un IDE de Java como IntelliJ IDEA o Eclipse

## Configuración del Entorno

Debe tener instalado Java 17 y configuradas las variables de entorno JAVA_HOME con la ruta de instalación de Java.

## Base de Datos

La aplicación utiliza una base de datos H2 en memoria. 

Los datos para acceder estan en el archivo **application.properties** del proyecto. 

La ruta de acceso a la base de datos es **localhost:8080/h2-console**, las credenciales estan en el archivo antes mencionado.

La base de datos se incluye como **data.sql** en el proyecto. Esta contiene información pre-cargada de usuarios y estados de tarea.

## Creación de la clave para generar los JWT

En esta aplicación, la generación y firma de los tokens JWT se maneja internamente utilizando una clave secreta gestionada por la aplicación en la clase JwtUtil, específicamente mediante la interfaz **SecretKeyProvider** y su implementación.

## Configuración del application.properties

En el archivo **application.properties** puedes encontrar la información de username y password para acceder a al consola de la base de datos H2, así como la ruta de acceso.

También puedes encontrar la configuración del tiempo de expiración para la clave JWT.

## Compilar la aplicación

En la terminal del proyecto ejecutar el comando **mvn clean install**

## Ejecutar la aplicación

En la terminal del proyecto ejecutar el comando **mvn spring-boot:run**

Asegúrate de que la aplicación esté en funcionamiento y que el servidor esté escuchando en el puerto 8080.

## Accede a Swagger UI:

Abre tu navegador y en la barra de direcciones, ingresa la siguiente URL: **http://localhost:8080/swagger-ui/index.html**

## Funcionamiento de la aplicación

Luego de levantar el proyecto y accediendo a **http://localhost:8080/swagger-ui/index.html** verá la interfaz de Swagger con la documentación, esta inicia en el controller de **Authentication Controller**, el cual pertenece a la definición de **public-api**, puede ver esto en la parte superior derecha en Swagger.

En este controller, que es el principal, se encuentra el endpoint **/auth/login**, el que permite obtener el token de **Autorización** para usuarios autenticados, es decir, usuarios que existen en la base de datos H2.

Una vez que obtiene el token en el response del endpoint deberá copiarlo para utilizarlo en los endpoint relacionados con la Gestión de Tareas. Estos endpoint protegidos se encuentran en la definición **secure-api**, en la parte superior derecha de Swagger.

Para acceder a los endpoint de Gestion de Tareas, debe seleccionar la definición **secure-api**, esto mostrará el controlador **Task Controller**. 

Antes de realizar cualquier prueba de los endpoint de Gestión de Tareas, debe presionar el botón **"Authorize"**, el que mostrará la ventana **"Available authorizations"**, aquí debe pegar el token obtenido anteriormente (No debe anteponer **"Bearer "** al token), luego hacer clic en el botón **"Authorize"** de la ventana para que guarde el token y finalmente clic al botón **"Close"** de la ventana mostrada. Esto guardará el token y permitirá utilizar todos los endpoint de este controller.

También es posible autorizar cada endpoint de manera independiente, asignando el token de forma similar, esto debe hacerlo para cada endpoint presionando el candado que se encuentra a la derecha en la cabecera del endpoint. Este proceso es opcional si quieres probar solo un endpoint, pero se recomienda la opción general.

Desde este controller podrá realizar todas las acciones relacionadas con la Gestión de Tareas, las cuales son:

- Listar Tareas
- Crear Tarea
- Modificar Tarea
- Obtener Tarea por su ID
- Eliminar Tarea por ID

## Validaciones de datos en los endpoints

>**Creación de Tareas**: Para crear una tarea los datos obligatorios son **"title"** y **"description"**. Las nuevas tareas se crean con **"status" "PENDIENTE"**.

>**Actualizar Tarea**: En este endpoint es obligatorio el campo **"information"** y el campo **"status"**, este último debe ser alguno de los mencionados en la documentación del endpoint.

## Uso de la API

**Autenticación**

Para obtener un token JWT, utiliza el endpoint de autenticación:

URL: POST /auth/login
Headers: Content-Type: application/json
Body:{
  "username": "user1",
  "password": "hola123"
}

Ejemplo de respuesta: 

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

**Operaciones de Tareas**

Para todas las operaciones con tareas, incluye el token JWT en el encabezado Authorization: Bearer <token>.

>Crear una tarea

URL: POST /api/tasks
Headers: Content-Type: application/json
Authorization: Bearer <token>
Body:{
  "title": "Nueva Tarea",
  "description": "Descripción de la tarea",
  "information": "Información adicional"
}

La respuesta será un código 201 indicando Tarea creada exitosamente. 

>Obtener todas las tareas

URL: GET /api/tasks
Headers:Authorization: Bearer <token>

La respuesta será un código 200 indicando Tareas encontradas o un código 404 No existen tareas.

>Obtener una tarea por ID

URL: GET /api/tasks/{id}
Headers: Authorization: Bearer <token>

La respuesta será un código 200 indicando Tarea encontrada o un código 404 Tarea no encontrada.

>Actualizar una tarea

URL: PUT /api/tasks/{id}
Headers:Content-Type: application/json
Authorization: Bearer <token>
Body:{
  "information": "Información actualizada",
  "status": "COMPLETADO"
}

La respuesta será un código 201 indicando Tarea actualizada exitosamente, código 400 indicando Solicitud inválida, código 404 indicando Tarea no encontrada.

>Eliminar una tarea

URL: DELETE /api/tasks/{id}
Headers:Authorization: Bearer <token>

La respuesta será un código 204 (No Content), indicando que la tarea ha sido eliminada correctamente.

## Archivos adjuntos en el proyecto

En la ruta **/src/main/resources** existen los siguientes archivos:

- data.sql
- Proyecto Nuevo SPA.postman_collection.json

El primero contiene la base de datos utilizada en el proyecto.

El segundo archivo es un proyecto de Postman en formato Json, en él se encuentran todos los endpoints ya configurados para realizar las pruebas del sistema. Considerar que se debe cambiar el token por el generado al autenticar al usuario.



##Información del Desarrollador

Este desarrollado fue realizado por **Mauricio Alfredo Toro Manríquez**. 

Si necesitas contactarme este es mi e-mail **mauricio.toro.m@gmail.com** para cualquier consulta o soporte relacionado con este proyecto.

