##

## Respuesta a desafío técnico de

Eduardo Rodríguez Bahamonde

La aplicación esta configurada para correr en el puerto 8989
Revisar application.properties para más detalles de la configuración general.

<br>
<br>
**SWAGGER-UI**

[http://localhost:8989/swagger-ui/index.html](http://localhost:8989/swagger-ui/index.html)

**Se dejan metodos tipo POST para crear y actualizar solo por un tema de facilidad de uso. Normalmente usaríoa PUT para los endpoints de solo actualización.**

**ENDPOINTS**
Autenticación: [POST] http://localhost:8989/authenticate
**BODY**

```
{
   "username": "eduardo",
   "password": "789"
}
```

<br>
<br>
**USUARIOS PRECARGADOS**

1. USERNAME: juan
   PASS: 123
2. USERNAME: andres
   PASS: 456
3. USERNAME: eduardo
   PASS: 789
4. USERNAME: javier
   PASS: 098
5. USERNAME: alejandro
   PASS: 765
6. USERNAME: judith
   PASS: 432

Puede usar uno de los usuarios precargados para utilizar la aplicación
o crear uno nuevo en el endpoint [POST] http://localhost:8989/usuarios
_(Los endpoints de CRUDS están protegidos por lo que requerirá autenticarse antes con alguno de los usuarios precargados)_
pasando un json como el siguiente:
<br>

```
{
   "username": "alejandra",
   "pass": "lale",
   "email": "lale@gmail.com"
}
```

Para crear tareas utilizar el endpoint [POST] http://localhost:8989/tareas
pasando un json como el siguiente:

**BODY**

```
{
    "nombre": "Tarea de Ejemplo",
    "descripcion": "Esta es una tarea de ejemplo",
    "usuario": {
        "id": 2
    },
    "estadoTarea": {
        "id": 1
    }
}

```

**Para actualizar las tareas utilizar el mismo endpoint entregando el objeto de Tarea completo, con su ID y los datos a cambiar.**
<br>
<br>

##

<br>
<br>

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

## Evaluación:

Se evaluará la solución en función de los siguientes criterios:

- Correcta implementación de las funcionalidades solicitadas.
- Aplicación de buenas prácticas de desarrollo, patrones de diseño y principios SOLID.
- Uso adecuado de Java 17, Spring Boot 2.7.x, H2, JWT, OpenAPI y Swagger.
- Claridad y completitud de la documentación.
