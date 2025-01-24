# Microservicio Gestión de Tareas


Este proyecto consiste en la creación de un microservicio backend utilizando Java 17 y Spring Boot 3, con maven para realizar las siguientes funciones principales:

1.-Validar las credenciales de los usuarios y generar un token JWT.

2.-Creación de tablas usuarios, tareas y estados.

3.- Metodos insertar, actualizar, eliminar y consultar

4.- Swagger disponible en http://localhost:8080/swagger-ui/index.html

4.- Base Datos H2 disponible en http://localhost:8080/h2-console


# Instalación del Proyecto

* Construye el proyecto utilizando Maven:
    * mvn clean install

* Ejecuta la aplicación:
    * mvn spring-boot:run

# Funcionamiento
  
  Se debe loguear en el Endpoint /login, posteriormente a eso obtiene un token, y ese es el que se usa para 
  consultar el resto de los servicios.

# API's 
  * Disponible en -> resources/Collection API






