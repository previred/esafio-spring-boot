# Proyecto Desafio Previred

Este proyecto es un desafío para la empresa Previred, desarrollado en Java con Spring Boot. El sistema permite la gestión de tareas y usuarios, proporcionando un sistema de autenticación con JWT.

## Requisitos

- **Java 17**
- **Spring Boot 3.4.1**
- **Maven 3.x**
- **Base de datos**: H2 (puede ser configurado a otra base de datos)
- **Swagger UI** para la documentación de la API

## Características

- Gestión de usuarios (login y autenticación mediante JWT)
- Gestión de tareas (crear, listar, actualizar y eliminar)
- Gestión de estados de tareas
- Seguridad mediante filtros JWT
- Documentación interactiva con Swagger UI

## Configuración de Maven
Este proyecto utiliza Maven como herramienta de construcción.
  - Navega al directorio del backend:
    ```bash
    cd desafio
    ```
  - Compila y ejecuta la aplicación con Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Uso de la API

### 1. Endpoint de Autenticación

**POST /auth/login**

- **Descripción**: Autenticación de usuario. Retorna un token JWT.
- **Cuerpo de la solicitud**:
  ```json
  {
    "username": "usuario",
    "password": "contraseña"
  }
  ```
- **Respuesta:**

```json
{
  "token": "jwt_token"
} 
```

### 2. Endpoint de Tareas

#### `GET /tareas`

- **Descripción**: Listar todas las tareas.

- **Respuesta**:
```json
[
  {
    "id": 1,
    "titulo": "Tarea 1",
    "descripcion": "Descripción de la tarea 1",
    "estado": {
      "id": 1,
      "nombre": "Pendiente"
    },
    "usuario": {
      "id": 1,
      "username": "usuario1"
    }
  }
]
```

#### `POST /tareas`

- **Descripción**: Crear una nueva tarea.

- **Cuerpo de la solicitud**:
  ```json
  {
    "titulo": "Nueva Tarea",
    "descripcion": "Descripción de la nueva tarea",
    "estado": {
      "id": 1,
      "nombre": "Pendiente"
    },
    "usuario": {
      "id": 1
    }
  }
  ```
- **Respuesta**:
```json
{
  "id": 2,
  "titulo": "Nueva Tarea",
  "descripcion": "Descripción de la nueva tarea",
  "estado": {
    "id": 1,
    "nombre": "Pendiente"
  },
  "usuario": {
    "id": 1,
    "username": "usuario1"
  }
}
 ```

#### `PUT /tareas/{id}`

- **Descripción**: Actualizar una tarea existente.

- **Cuerpo de la solicitud** (igual que el de `POST /tareas`):
  ```json
  {
    "titulo": "Tarea actualizada",
    "descripcion": "Descripción de la tarea actualizada",
    "estado": {
      "id": 1,
      "nombre": "Pendiente"
    },
    "usuario": {
      "id": 1
    }
  }
  ```
- **Respuesta**:
```json
{
  "id": 1,
  "titulo": "Tarea actualizada",
  "descripcion": "Descripción de la tarea actualizada",
  "estado": {
    "id": 1,
    "nombre": "Pendiente"
  },
  "usuario": {
    "id": 1,
    "username": "usuario1"
  }
}
```
#### `DELETE /tareas/{id}`

- **Descripción**: Eliminar una tarea por su ID.

- **Respuesta**:
  ```json
  {
    "message": "Tarea eliminada con éxito"
  }
  ```


## Notas Adicionales

- Dentro del proyecto se encuentra el archivo **PreviredDesafioPostman.json** donde se encuentra la colección para importar a Postman.
