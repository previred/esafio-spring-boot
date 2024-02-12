# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.

## Objetivo:
Crear una API RESTful utilizando Spring Boot 2.7.x que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## Requisitos Técnicos:
### Java:
- Utiliza Java 17 para la implementación.
- Utiliza las características de Java 17, como lambdas y streams, cuando sea apropiado.
- Utilizar Maven como gestor de dependencias

### Spring Boot 2.7.x:
- Construye la aplicación utilizando Spring Boot 2.7.x (última versión disponible).

### Base de Datos:

- Utiliza una base de datos H2.
- Crea tres tablas: usuarios, tareas y estados_tarea.
- La tabla usuarios debe contener datos pre cargados.
- La tabla estados_tarea debe contener estados pre cargados.

### JPA:
- Implementa una capa de persistencia utilizando JPA para almacenar y recuperar las tareas.

### JWT (JSON Web Token):

- Implementa la autenticación utilizando JWT para validar usuarios.

### OpenAPI y Swagger:

- Documenta la API utilizando OpenAPI y Swagger.

## Funcionalidades:
### Autenticación:
- Implementa un endpoint para la autenticación de usuarios utilizando JWT. 

### CRUD de Tareas:
- Implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las tareas.

## Consideraciones:
### Seguridad:
- Asegúrate de que las operaciones CRUD de tareas solo sean accesibles para usuarios autenticados.

### Documentación:
- Utiliza OpenAPI y Swagger para documentar claramente la API.
- Puntos adicionales si se genera el API mediante metodologia API First. Generar el archivo openapi.yml Nota: Ejemplo Plugin Maven groupId org.openapitools, artifactId openapi-generator-maven-plugin

### Código Limpio:
- Escribe código ordenado, aplicando buenas prácticas y principios SOLID.

### Creatividad
- Se espera dada la descripción del problema se creen las entidades y metodos en consecuencia a lo solicitado.

## Entregables:
### Repositorio de GitHub:
- Realiza un Pull request a este repositorio indicando tu nombre, correo y cargo al que postulas.
- Todos los PR serán rechazados, no es un indicador de la prueba.

### Documentación:
- Incluye instrucciones claras sobre cómo ejecutar y probar la aplicación.
- **Incluir Json de prueba en un archivo texto o mediante un proyecto postman** Nota: Si no va se restaran puntos de la evaluación

## Evaluación:
Se evaluará la solución en función de los siguientes criterios:

- Correcta implementación de las funcionalidades solicitadas.
- Aplicación de buenas prácticas de desarrollo, patrones de diseño y principios SOLID.
- Uso adecuado de Java 17, Spring Boot 2.7.x, H2, JWT, OpenAPI y Swagger.
- Claridad y completitud de la documentación.
- **Puntos extras si la generación de la API se realizo mediante API First**


**********************************************************************************************

## Comenzando 🚀
- El siguiente desafío tiene como fin, demostrar mis habilidades tecnicas.

## Implementación 🛠️
    
- Se realizo un código muy organizado y aplicando buenas prácticas de codificación (Código Limpio, Principios SOLID, Patrones de Diseño y Programación Funcional) para un código mantenible y testeable.

- También se realizaron algunas Pruebas Unitarias para el Dominio de Task (Tareas).

## Despliegue 📦

* Paso 1: clonar el proyecto
* Paso 2: Ejecutar el proyecto en un IDE
* Paso 3: Abrir Postman y ejecutar los endpoints (Se ajunta Collección)

## Tecnologías Utilizadas 📋
- Java 17
- Spring Boot 2.7.18
- H2
- Swagger
- JUnit y Mockito


## Información 📖

Cualquier duda, comunicarse al correo: vasquezruben9412@gmail.com

