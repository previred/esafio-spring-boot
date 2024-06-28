# Aplicación de Gestión de Tareas

## Descripción

Esta es una aplicación RESTful para la gestión de tareas que permite crear, leer, actualizar y eliminar tareas desde una base de datos H2. La aplicación utiliza Spring Boot 2.7.18, Java 17, Maven, JPA, y JWT para la autenticación y seguridad. La documentación de la API se realiza mediante OpenAPI y Swagger.

## Requisitos

- Java 17
- Maven 3.x

## Instalación y Ejecución

1. **Compilar y empaquetar la aplicación**

    ```sh
    mvn clean install
    ```

2. **Ejecutar la aplicación**

    ```sh
    mvn spring-boot:run
    ```

   La aplicación estará disponible en `http://localhost:8080/taskManager/api/v1/`.

## Pruebas

Para probar la aplicación, puedes utilizar Postman. A continuación, se incluyen instrucciones para importar un archivo de colección de Postman y algunos ejemplos de JSON para pruebas.

### Importar colección de Postman

1. **Descargar la colección de Postman**

   Descarga el archivo `taskManager.postman_collection.json`.

2. **Importar la colección en Postman**

    - Abre Postman.
    - Haz clic en "Importar".
    - Selecciona el archivo `taskManager.postman_collection.json` y haz clic en "Abrir".
    - Allí encontraras información de como testear la aplicación