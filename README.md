# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.

## Objetivo:
Crear una API RESTful utilizando Spring Boot 2.7.x que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## Se realizo la prueba Satisfactoriamente, utilizando API First y todas las tecnologias utilizadas  
la url base es : http://localhost:8080/

Los endpoints son :

/login

- para poder loguearse a la aplicaion previamente deben haber datos pre cargados y se verifica el email y password del usuario
para realizar un login exitoso, posteriormente se le asigna un token que servira para poder acceder a los otros endpoints

/listar-tareas/{id}

- Requiere token de acceso lista todas las tareas pertenecientes al usuario activo

/crear-tarea

- Requiere token de acceso, Crea una nueva tarea, que debe tener relacion con el usuario actual y un estado de tarea, *(Existen estados de tarea precargados)

/actualizar-tarea/{id}

- Requiere token de acceso, Actualiza una tarea existente, que debe tener relacion con el usuario actual y un estado de tarea

/borrar-tarea/{id}

- Requiere token de acceso, Borra una tarea existente, que debe tener relacion con el usuario actual y un estado de tarea

- cualquier otro enpoint se controla con el codigo 403 acceso prohibido (a excepcion de swwager openAPI, y h2 Console que es la Base de datos en memoria)

## Inicializar el proyecto
Se deben descargar las librerias necesarias que usa el proyecto, Usando Maven

        mvn clean install

### Estructura del Proyecto
com/desafio/desafiospringboot/controller

- Aqui estan todos los controladores TareaController y UsuarioController

com/desafio/desafiospringboot/model

- Aqui esta la logica de negocio, peticiones y manipulaciones de datos

com/desafio/desafiospringboot/model/services/abstractfactory

- Aqui se hace uso del patron de diseño AbstractFactory

test/java/com/desafio/desafiospringboot/model/services

- Aqui se hace uso de pruebas unitarias usando Junit5 y Mockito

src/main/resources/openapi.yaml

- Este es el archivo utilizado para la generacion y documentacion del Api first

src/main/resources/data.sql

- Aqui se cargan los datos de Usuario y estados de tarea

### Archivo de prueba json generado por postman

API documentation.postman_collection.json



