# Desafío Técnico: Gestión de Tareas con Spring Boot y Java


Api Rest realizada con:

- Java 17
- SpringBoot v3.4.1
- Maven
- Spring Security

## Importante

- Archivo postman con el nombre "Previred Gestión de Tareas.postman_collection.json"
- Archivo yaml con el nombre "openapi.yaml"
- Ruta para Swagger /swagger-ui/index.html
- No se realizo con Api First

##Destacados

- Se creo un DTO para respuestas de la api pero solo se implemento en Auth no en los endpoints de Tasks
- Se utiliza patron DTO
- Se utiliza patron DAO
- Los usuarios no tienen roles ni permisos definidos, solo por defecto
- Solo se puede hacer CRUD de Tasks incluido el id de Tasks States las cuales estas precargadas

## Ejecución
 
 - Importar proyecto a ide de preferencia como intellij o vs code + plugins requeridos y ejecutar. Esto levantara la aplicacion por defecto en el puerto 8080
 - Para probar con docker debemos utilizar la terminal estando dentro del proyecto y generar el .jar con "./mvnw clean package", luego construir la imagen con "docker build -t gestion-tareas ." y finalmente ejecutar el contenedor con "docker run -p 8080:8080 gestion-tareas"
 -  Debes registrarte y luego iniciar sesion para obtener el token jwt de acceso