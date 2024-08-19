# Spring Boot Task Manager Project

Este proyecto es un gestor de tareas desarrollado con Spring Boot. A continuación, se detallan los pasos para su instalación, configuración y pruebas.

## Requisitos Previos

- Java 17 o superior.
- Maven 3.5 o superior.

## Instalación

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/cnorellanan/desafio-spring-boot.git
   cd desafio-spring-boot
   ```

2. **Compilar y empaquetar el proyecto:**

   ```bash
   mvn clean package
   ```

3. **Ejecutar la aplicación:**

   ```bash
   java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
   ```

## Carga Inicial de Datos

Al iniciar la aplicación, se precargarán automáticamente las siguientes tablas con los datos iniciales:

- **task_status:** Contiene los estados posibles para las tareas.
- **user_entity:** Contiene los usuarios del sistema.

## Usuarios de Prueba

Los usuarios pre-cargado automaticamente para pruebas son los siguientes:

- `usuario1@mail.com` : `Agosto2024`
- `usuario2@mail.com` : `Agosto2024`
- `usuario3@mail.com` : `Agosto2024`

## Documentación de la API

La documentación de la API generada con Swagger se encuentra disponible en la siguiente URL una vez que el proyecto esté en ejecución:

```
http://localhost:8080/task-manager/swagger-ui/
```

## Probar los Servicios

En la carpeta `/Postman` de este proyecto, encontrarás un archivo con las colecciones de Postman que te permitirá probar todos los servicios expuestos por la API.