# Documentación API de Gestión de Tareas

## Índice
1. [Introducción](#1-introducción)
2. [Configuración Técnica](#3-configuración-técnica)
3. [Autenticación](#4-autenticación)
4. [Creación de Tareas](#5-creación-de-tareas)
5. [Consulta de Tareas](#6-consulta-de-tareas)
6. [Actualización de Tareas](#7-actualización-de-tareas)
7. [Eliminación de Tareas](#8-eliminación-de-tareas)

## 1. Introducción
La API de Gestión de Tareas está diseñada para facilitar la creación, actualización, eliminación y consulta de tareas dentro de una organización. Permite a los usuarios autenticados manejar tareas asignadas a ellos o a sus equipos, mejorando la productividad y la organización del trabajo.

## 2. Configuración Técnica

### Requisitos Técnicos
- **Java 17** para aprovechar las últimas características del lenguaje.
- **Spring Boot 2.7.x** como framework principal para el desarrollo de la aplicación.
- **Base de Datos H2** para la persistencia de datos.
- **JPA** para la capa de persistencia, facilitando la interacción con la base de datos.
- **JWT** para la autenticación de usuarios y la seguridad de la API.
- **OpenAPI y Swagger** para la documentación de la API, proporcionando una interfaz interactiva para explorar las capacidades de la API.

### Funcionalidades
- **Autenticación de usuarios** con JWT para acceder a las operaciones CRUD de tareas.
- **Gestión de tareas** permitiendo a los usuarios crear, leer, actualizar y eliminar tareas.


Variables de entorno utilizadas en la aplicación:

| Variable                           | Descripción                                           | Valor por Defecto                           |
|------------------------------------|-------------------------------------------------------|---------------------------------------------|
| `SERVICE_PORT`                     | Puerto para el servidor HTTP.                         | `8080`                                      |
| `SERVICE_CONTEXT`                  | Contexto base para la API.                            | `/task-management`                          |
| `DB_USER`                          | Nombre de usuario para la base de datos H2.           | `sa`                                        |
| `DB_PASSWORD`                      | Contraseña para la base de datos H2.                  | `Admin1003.,`                               |
| `DB_HIBERNATE_DDL_AUTO`            | Configuración de Hibernate para la inicialización DB. | `create`                                    |
| `H2_CONSOLE_ENABLED`               | Habilita la consola H2.                               | `true`                                      |
| `H2_CONSOLE_PATH`                  | Ruta para la consola H2.                              | `/h2-console`                               |
| `JWT_SECRET`                       | Clave secreta para JWT.                               | `#shdAKycCY;7{]$BZ((uMRR>d$2LsQv7=_xtg4lCt` |
| `JWT_EXPIRATION_TIME`              | Tiempo de expiración para JWT en segundos.            | `86400` (24 horas)                          |

## 3. Autenticación

Para acceder a los endpoints protegidos es necesario autenticarse. A continuación, se describe el endpoint de autenticación y los campos requeridos.

### Endpoint de Autenticación

**POST** `/api/v1/auth/authenticate`

#### Request

| Campo      | Tipo   | Descripción            | Requerido |
|------------|--------|------------------------|-----------|
| `username` | String | Nombre de usuario.     | Sí        |
| `password` | String | Contraseña del usuario.| Sí        |

##### Ejemplo de Request

```json
{
   "username": "user1",
   "password": "1234"
}
```

#### Response

| Campo | Tipo   | Descripción                                   |
|-------|--------|-----------------------------------------------|
| `jwt` | String | Token JWT generado para la sesión del usuario.|

##### Ejemplo de Response

```json
{
   "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTYwOTU0OTIzNSwiZXhwIjoxNjA5NjM1NjM1fQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```
**Nota:** El token JWT debe enviarse en la cabecera de autorización en formato "Bearer".


## 4. Creación de Tareas

Permite a los usuarios autenticados crear una nueva tarea.

### Endpoint de Creación de Tareas

**POST** `/api/v1/task`

#### Request

| Campo         | Tipo   | Descripción                         | Requerido |
|---------------|--------|-------------------------------------|-----------|
| `title`       | String | Título de la tarea.                 | Sí        |
| `description` | String | Descripción detallada de la tarea.  | Sí        |
| `state`       | String | Estado inicial de la tarea.         | Sí        |
| `username`    | String | Nombre de usuario que crea la tarea.| Sí        |

##### Ejemplo de Request

```json
{
   "title": "Finalizar documentación del proyecto",
   "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
   "state": "PENDIENTE",
   "username": "user1"
}
```

#### Response

| Campo          | Tipo   | Descripción                             |
|----------------|--------|-----------------------------------------|
| `id`           | UUID   | Identificador único de la tarea creada. |
| `title`        | String | Título de la tarea.                     |
| `description`  | String | Descripción de la tarea.                |
| `creationDate` | Date   | Fecha de creación de la tarea.          |
| `state`        | String | Estado actual de la tarea.              |
| `user`         | String | Usuario que creó la tarea.              |

##### Ejemplo de Response

```json
{
   "id": "076b07ba-e573-4979-b4c9-5871278d8c81",
   "title": "Finalizar documentación del proyecto",
   "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
   "creationDate": "2024-02-12T23:22:09.5084967",
   "state": "PENDIENTE",
   "user": "user1"
}
```

## 5. Consulta de Tareas

Permite a los usuarios autenticados obtener el listado de tareas.

### Endpoint de Consulta de Tareas

**GET** `/api/v1/task`

#### Response

| Campo          | Tipo    | Descripción                             |
|----------------|---------|-----------------------------------------|
| `id`           | UUID    | Identificador único de la tarea.        |
| `title`        | String  | Título de la tarea.                     |
| `description`  | String  | Descripción de la tarea.                |
| `creationDate` | Date    | Fecha de creación de la tarea.          |
| `state`        | String  | Estado actual de la tarea.              |
| `user`         | String  | Usuario que creó la tarea.              |

##### Ejemplo de Response

```json
[
   {
      "id": "076b07ba-e573-4979-b4c9-587134234324",
      "title": "Finalizar documentación del proyecto",
      "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
      "creationDate": "2024-02-12T23:22:09.508497",
      "state": "PENDIENTE",
      "user": "user1"
   },
   {
      "id": "076b07ba-e573-4979-b4c9-5871278d8c81",
      "title": "Finalizar documentación del proyecto",
      "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
      "creationDate": "2024-02-12T23:22:09.508497",
      "state": "PENDIENTE",
      "user": "user2"
   }
]
```

## 6. Actualización de Tareas

Permite a los usuarios autenticados actualizar los detalles de una tarea específica.

### Endpoint de Actualización de Tareas

**PUT** `/api/v1/task/{taskId}`

#### Request

| Campo         | Tipo   | Descripción                                | Requerido |
|---------------|--------|--------------------------------------------|-----------|
| `taskId`      | UUID   | Identificador único de la tarea a actualizar. | Sí (en URL) |
| `title`       | String | Nuevo título de la tarea.                  | No        |
| `description` | String | Nueva descripción de la tarea.             | No        |
| `state`       | String | Nuevo estado de la tarea.                  | Sí        |
| `username`    | String | Nombre de usuario que actualiza la tarea.  | Sí        |

##### Ejemplo de Request

```json
{
   "title": "Finalizar documentación del proyecto",
   "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
   "state": "COMPLETADO",
   "username": "user1"
}
```

#### Response

La respuesta incluye los detalles de la tarea después de la actualización.

##### Ejemplo de Response

```json
{
   "id": "076b07ba-e573-4979-b4c9-5871278d8c81",
   "title": "Finalizar documentación del proyecto",
   "description": "Se debe completar la documentación del proyecto incluyendo los últimos cambios en la API.",
   "creationDate": "2024-02-12T23:22:09.5084967",
   "state": "COMPLETADO",
   "user": "user1"
}
```

## 7. Eliminación de Tareas

Permite a los usuarios autenticados eliminar una tarea específica.

### Endpoint de Eliminación de Tareas

**DELETE** `/api/v1/task/{taskId}`

#### Request

El único parámetro requerido es el `taskId`, que debe incluirse en la URL.

##### Ejemplo de URL

`/api/v1/task/c5485f0d-e4d5-4ef7-b031-9b786449fab1`

#### Response

La respuesta es un mensaje confirmando la eliminación exitosa de la tarea.

##### Ejemplo de Response

```json
{
   "timestamp": "2024-02-12T23:51:22.6753423",
   "message": "Tarea eliminada con exito"
}
```

