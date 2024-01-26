
# Previred Desafio

Desafío Técnico: Gestión de Tareas con Spring Boot y Java



## Descripcion de la solicitud

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.


## Requisitos Previos

Requisitos para instalacion.

- Java JDK 17
- Maven
- Docker

## Configuración del Proyecto

1. **Clona el Repositorio:**
   ```bash
   git clone https://github.com/enfralys/desafio-spring-boot
   cd desafio-spring-boot


2. **Compila el Proyecto:**
   ```bash
   mvn clean package o mvn clean install


3. **Corre el proyecto:**
   ```bash
   mvn spring-boot:run

1. **proyecto con docker :**
   ```bash
   Docker build -t {previred}  .  

   Docker run -p 8080:8080 previred

## Dependencias

Este proyecto utiliza las siguientes dependencias:

- [Spring Boot Data JPA](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-jpa-and-spring-data)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
- [jjwt](https://github.com/jwtk/jjwt) - JSON Web Tokens para autenticación
- [springdoc-openapi-ui](https://springdoc.org/) - Para la documentación de la API con OpenAPI y Swagger
- [Spring Boot Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html)
- [Log4j](https://logging.apache.org/log4j/2.x/)
- [Java Validation API](https://beanvalidation.org/)
- [Spring Boot WebFlux](https://docs.spring.io/spring-boot/docs/current/reference/html/web-reactive.html)
- [Spring Boot Security](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-security)
- [MapStruct](https://mapstruct.org/) - Para mapeo de objetos
- [Project Reactor](https://projectreactor.io/) - Reactor Core para programación reactiva
- [H2 Database](https://www.h2database.com/html/main.html) - Base de datos embebida para desarrollo y pruebas
- [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing)
- [Lombok](https://projectlombok.org/) - Anotaciones para reducir código boilerplate
- [JetBrains Annotations](https://mvnrepository.com/artifact/org.jetbrains/annotations) - Anotaciones de JetBrains para herramientas de desarrollo

## H2 CONSOLE

Para el uso de la consola de h2 tienes las siguientes propiedades

[Consola H2](http://localhost:8080/h2-console)

`user` sa

`password` sa

## Usuario precargado 

`url`: http://localhost:8080/login

`Usuario`: admin@test.com

`Password` : Canaima23.

[Swagger](http://localhost:8080/swagger-ui/index.html)

JSON de postman en la carpeta api doc



## Creador

Enfranly Leonardo Monzon Pineda

Enfralyss@gmail.com

Java Developer

