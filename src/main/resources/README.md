# Move Apps Challenge

Bienvenido a Move Apps Challenge, una aplicación Java desarrollada con Spring Boot.

## Requisitos previos
- Java Development Kit (JDK) 17 o superior instalado en tu sistema.
- Postman instalado en tu sistema si deseas probar los endpoints de la API.

## Instrucciones para descargar y ejecutar la aplicación

### Descarga del código fuente
Puedes clonar este repositorio utilizando Git. Abre una terminal y ejecuta el siguiente comando:

git clone https://github.com/tu_usuario/move-apps-challenge.git

### Ejecución desde la línea de comandos
1. Navega hasta el directorio raíz del proyecto `move-apps-challenge`.
2. Ejecuta el siguiente comando para compilar y empaquetar la aplicación:
./mvnw clean package

3. Una vez compilado, puedes ejecutar la aplicación con el siguiente comando:
   `java -jar target/challenge-0.0.1-SNAPSHOT.jar`

### Ejecución desde un IDE (IntelliJ IDEA)
1. Abre IntelliJ IDEA y selecciona "Open" en el menú principal.
2. Navega hasta el directorio donde has clonado el repositorio y selecciona el archivo `pom.xml`. Haz clic en "Open" para abrir el proyecto.
3. En el panel de proyectos, busca la clase `ChallengeApplication` dentro de la ruta `src/main/java/com/move/challenge`.
4. Haz clic derecho en la clase `ChallengeApplication` y selecciona "Run".

### Carga de usuarios en la base de datos H2
- Al levantar la aplicación, la base de datos H2 llamada "challenge" se precargará con 5 usuarios. Los detalles de los usuarios son los siguientes:
    1. Nombre de usuario: "Usuario 0", Email: "email0@email.com", Clave: "clave0"
    2. Nombre de usuario: "Usuario 1", Email: "email1@email.com", Clave: "clave1"
    3. ...
    4. Nombre de usuario: "Usuario 4", Email: "email4@email.com", Clave: "clave4"
- La tabla de Estados Tarea tambien se precargará al levantar la aplicación.

### Obtención de token de autenticación
- Una vez que la aplicación se haya levantado correctamente, es necesario obtener un token de autenticación para acceder a los endpoints privados. Esto se puede hacer mediante el endpoint `/api/public/auth/login`, que se encuentra documentado en la colección de Postman o en Swagger.

### Uso del token de autenticación
- El token obtenido debe ser inyectado como un token de tipo Bearer para todos los endpoints del API privada, es decir, aquellos que comiencen con `/api/private/...`.
### Uso de Postman
1. Abre Postman en tu sistema.
2. Importa la colección de endpoints proporcionada ubicada en `src/main/resources/Move App Challenge - Eibert.postman_collection.json`.
3. Una vez importada, encontrarás todos los endpoints con datos de ejemplo en la colección de Postman.

## Documentación de la API con Swagger
También puedes acceder a la documentación de la API utilizando Swagger. Abre tu navegador web y visita la siguiente URL:

[http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
