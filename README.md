# Gestión de Tareas con Spring Boot y Java 🚀

## 📜 Descripción del Proyecto
Este proyecto aborda el desafío técnico propuesto por NUEVO SPA, destinado a desarrollar una **plataforma de gestión de tareas** para optimizar la productividad de los equipos. Implementado con **Spring Boot** y **Java 17**, este sistema no solo permite a los usuarios manejar tareas (crear, actualizar, listar, eliminar) de manera eficiente sino que también asegura la autenticación mediante **JWT**. Además, toda la API está documentada utilizando **OpenAPI** y **Swagger**, facilitando su comprensión y uso.

## ✨ Características
- **Autenticación JWT:** Para un acceso seguro.
- **CRUD de Tareas:** Permite la gestión completa de tareas.
- **Documentación con Swagger:** Facilita la prueba y entendimiento de la API.
- **H2 Database:** Base de datos en memoria para un desarrollo ágil.

## 🛠️ Tecnologías Utilizadas
- **Java 17:**
- **Spring Boot 2.7.18:** 
- **Base de Datos H2:** 
- **JPA:** 
- **JWT:** 
- **OpenAPI y Swagger:** 

## 📦 Descarga e Instalación
Para obtener el proyecto, clona el repositorio de GitHub:
```shell
https://github.com/BAguileraSilva/desafio-spring-boot.git
```

## 🚀 Compilación y Ejecución
Asegúrate de tener [Maven](https://maven.apache.org/) instalado. Desde el directorio raíz del proyecto, ejecuta:
```shell
mvn clean install
mvn spring-boot:run
```

## 🧪 Pruebas
Encontrarás un archivo JSON con la coleccion para cargan en [Postman](https://www.postman.com/) . **Pasos esenciales:**
1. **Loguearse** para obtener un token JWT (Login ok).
2. **Usar el token Bearer** en las peticiones de la colección de Tareas.
3. **CRUD Tareas** Crear una tarea (en el header de respuesta se adjunta el id generado), a partir de eso experimentar con el resto del CRUD.

### 🗃️ Acceso a la Base de Datos H2
Visita http://localhost:8080/h2-console/ con estos datos:
- **Driver Class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `admin`
- **Password:** `Ac9732`

### 📝 Datos Precargados
Para facilitar las pruebas, la base de datos se inicializa con **usuarios** y **estados de tarea** predefinidos. Utiliza estos para probar las funcionalidades CRUD y de autenticación.

Tabla Usuarios

| ID_USER    | NOMBRE_COMPLETO  | email                | PASSWORD | FECHA_CREACION |
|------------|------------------|----------------------|----------|----------------|
| 12345678-1 | Nombre Usuario 1 | usuario1@example.com | pass1234 | 2024-01-01     |
| 98765432-2 | Nombre Usuario 2 | usuario2@example.com | pass4567 | 2024-01-02     |
| 45367812-3 | Nombre Usuario 3 | usuario3@example.com | pass7890 | 2024-01-03     |


Tabla Estados

| ESTADO_TAREA | DESCRIPCION_ESTADO                  |
|--------------|-------------------------------------|
| INI          | Tarea nueva                         |
| PRO          | Tarea actualmente en progreso       |
| SUS          | Tarea momentaneamente suspendida    |
| FIN          | Tarea completada satisfactoriamente |


### 📘 Documentación Swagger
Accede a la documentación y pruebas de la API en http://localhost:8080/swagger-ui.html después de iniciar el proyecto.

## 🌟 API First
Desarrollado bajo la metodología API First, utilizando [apibldr](https://www.apibldr.com/) y [SwaggerEditor](https://editor-next.swagger.io/). El archivo `OpenApi.yaml` se encuentra en `resources/static`, y el código se genero automaticamente al compilar y posteriormente se comento la linea de generacion automatica para evitar errores.


