
#  Datos de desafio
    nombre y apellido: Wilbert Acosta
    correo: WilbertAcosta@gmail.com
    cargo: Java Developer
---
# Task Manager

Este proyecto es un administrador  de tareas desarrollado en Spring Boot. Proporciona una API REST para la gestión de tareas y utiliza Spring Data JPA para la persistencia de datos. También incluye seguridad mediante Spring Security con JSON Web Tokens (JWT).


## Requisitos del Proyecto
- Java 17
- Spring Boot 2.7.18
- Maven
- H2 Database 2.2.220
- Lombok
- Springdoc OpenAPI UI 1.7.0
- Spring Boot DevTools
- Mockito 3.9.0
- Builder: paketobuildpacks/builder-jammy-base:latest

## Configuración

El proyecto utiliza Maven para la gestión de dependencias y construcción. Puedes utilizar el wrapper de Maven (mvnw o mvnw.cmd) para ejecutar comandos Maven sin necesidad de instalar Maven en tu sistema.

### Perfiles
- **prod:** Perfil de producción que activa la configuración correspondiente.

## Dependencias Principales
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Security
- Spring Boot Starter Web
- JSON Web Token (JJWT) 0.12.3
- H2 Database
- Lombok
- Springdoc OpenAPI UI
- Spring Boot Starter Test
- Spring Security Test
- Mockito 3.9.0
- Mockito Inline 3.9.0

## openApi  ubicacion (auto-generado)
```
task-manager
  |-- ci
  |   |-- openApi
  |       `-- spec.yaml
  ```


# COMANDOS

### Ejecutar la aplicación con profile default
````./mvnw clean spring-boot:run````
### Ejecutar la aplicación con profile prod
``SPRING_PROFILES_ACTIVE=prod ./mvnw clean spring-boot:run   
``
## Ejecutar Unit Test
``./mvnw  surefire:tes``

## Ejecutar Integration Test
``./mvnw failsafe:integration-test``


# Configuración de Base de Datos
- Base de datos H2 en memoria
- Usuario: `sa`
- Contraseña: `sa`
- JDBC URL: `jdbc:h2:mem:testdb` 

## Configuración de Seguridad
- Generación y validación de JWT
- Clave secreta: `umjld9Nh7n71RrNWTdABxHYpqWvXFouZ7twsuVPxXkwTctzGoDnnC7vMzTlAFPR5W39IUGso0M46x8iJOx1`

## URL Locales al Iniciar el Perfil  default (cuando se activa el perfil prod, se desactivan dichas urls)

- [Swagger UI](http://localhost:8092/api/task-manager/v1/swagger-ui/index.html)
- [API Docs](http://localhost:8092/api/task-manager/v1/api-docs)
- [H2 Console](http://localhost:8092/api/task-manager/v1/h2-console/)

## Estructura del proyecto
```yaml
task-manager
  |-- .idea
  |-- .mvn
  |-- ci
  |   |-- openApi
  |       `-- spec.yaml
  |-- src
  |   |-- intTest
  |   |   `-- java
  |   |       `-- rest
  |   |           `-- AuthenticationControllerIT
  |   `-- main
  |       `-- java
  |           `-- taskmanager
  |               |-- configuration
  |               |   |-- openapi
  |               |   |   |-- OpenApiConfig
  |               |   |   `-- OpenApiInfoProps
  |               |-- persistence
  |               |   `-- PersistTaskStatusConfig
  |               |-- security
  |               |   |-- AuthenticationConfig
  |               |   |-- JwtAuthenticationFilter
  |               |   `-- SecurityConfig
  |               |-- user
  |               |   |-- DemoDataConfig
  |               |   `-- DemoUserConfigProperties
  |               |-- controller
  |               |   `-- rest
  |               |       |-- advice
  |               |       |   `-- BaseRestControllerAdvice
  |               |       |-- AuthenticationController
  |               |       |-- TaskController
  |               |       `-- UserController
  |               |-- validator
  |               |   |-- UserRegistryValidator
  |               |   `-- ValidUserRegistry
  |               |-- domain
  |               |   |-- common
  |               |   |   |-- ApiBaseExceptionDetail
  |               |   |   |-- RolePermission
  |               |   |   |-- TaskStatus
  |               |   |   `-- UserRole
  |               |-- dto
  |               |   |-- AuthenticationRequestDto
  |               |   |-- TaskDto
  |               |   |-- UserDto
  |               |   `-- UserRegistryDto
  |               |-- entity
  |               |   |-- TaskEntity
  |               |   |-- TaskStatusEntity
  |               |   |-- UserAuthDetailsEntity
  |               |   `-- UserEntity
  |               |-- exception
  |               |   |-- BaseException
  |               |   |-- NotFoundTaskException
  |               |   `-- UserNotFoundException
  |               |-- mapper
  |               |   |-- BiMapper
  |               |   |-- Mapper
  |               |   |-- TaskDtoToTaskEntityMapper
  |               |   |-- TaskEntityToTaskDtoMapper
  |               |   |-- UserDtoToUserEntityMapper
  |               |   |-- UserEntityToUserDto
  |               |   `-- UserRegistryDtoToUserEntity
  |               |-- repository
  |               |   |-- TaskRepository
  |               |   |-- TaskStatusRepository
  |               |   `-- UserRepository
  |               |-- service
  |               |   |-- AuthenticationService
  |               |   |-- JwtService
  |               |   |-- TaskService
  |               |   |-- UserDetailsAuthenticationService
  |               |   `-- UserService
  |-- resources
  |   |-- demo
  |   |   |-- users.yaml
  |   |   `-- users-prod.yaml
  |   |-- application.yaml
  |   `-- application-prod.yaml
  |-- test
  |   `-- java
  |       `-- taskmanager
  |           |-- rest
  |           |   `-- advice
  |           |       |-- BaseRestControllerAdviceTest
  |           |       |-- AuthenticationControllerTest
  |           |       |-- TaskControllerTest
  |           |       `-- UserControllerTest
  |           |-- mapper
  |           |   |-- TaskDtoToTaskEntityMapperTest
  |           |   |-- TaskEntityToTaskDtopMapperTest
  |           |   |-- UserDtoToUserEntityMapperTest
  |           |   |-- UserEntityToUserDtoTest
  |           |   `-- UserRegistryDtoToUserEntityTest
  |           |-- service
  |           |   |-- AuthenticationServiceTest
  |           |   |-- JwtServiceTest
  |           |   |-- TaskServiceTest
  |           |   |-- UserDetailsAuthenticationServiceTest
  |           |   `-- UserServiceTest
```


