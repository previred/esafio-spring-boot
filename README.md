# Gestión de Tareas

Este es un proyecto de gestión de tareas desarrollado con Spring Boot.

## Funcionalidades

- Crear tarea `POST::/gestion/tasks`
- Actualizar tarea `PUT::/gestion/tasks/{taskId}`
- Leer tarea `GET::/gestion/tasks/{taskId}`
- Leer tarea de un usuario `GET::/gestion/tasks/user/{userId}`
- Leer todas las tareas `GET::/gestion/tasks`
- Eliminar tarea `DELETE::/gestion/tasks/{taskId}`
- Leer usuarios `GET::/gestion/users`
- Leer usuario por nombre `GET::/gestion/users/{username}`
- Autenticación de usuarios. `POST::/gestion/authenticate`

## Tecnologías Utilizadas

- Java 17
- Spring Boot 2.7.18
- Spring Security + librería jjwt para la autenticación mediante tokens
- H2 Database para la persistencia en memoria
- JPA
- openApi
- MapStruct para el mapeo de entidades a DTOs
- Lombok para la reducción de código boilerplate

## Configuración

### Base de Datos

El proyecto utiliza una base de datos H2 en memoria. No es necesario configurar ninguna base de datos externa. Puedes acceder a la consola de la base de datos en `http://localhost:8080/h2-console` con las siguientes credenciales:

- URL: `jdbc:h2:mem:midb`
- Usuario: `usuario`
- Contraseña: `password`

### Autenticación

La autenticación se realiza mediante JSON Web Tokens (JWT). Para autenticar un usuario, realiza una solicitud POST a `http://localhost:8080/api/authenticate` con el siguiente cuerpo:

```json
{
    "username": "usuario1",
    "password": "password1"
}
```
### Ejecución

Para ejecutar el software se debe compilar y actualizar maven para luego darle run desde GestionApplication.

### Uso de los Endpoints
 
Dentro de la ruta `/resources` está el archivo de collection en postman y el enviroment, estos se deben importar al postman.
De todas formas, los endpoints también pueden ser probados desde swagger http://localhost:8080/swagger-ui siempre y cuando accediendo 
al endpoint `/gestion/authenticate` y autorizando con token en la siguiente imagen


![img.png](src/main/resources/img.png)

Es importante señalar que antes de utilizar cualquier endpoint primero se debe autenticar.