**********************************************************************************************************************************************************************************

# Pasos para Configurar y Ejecutar la API
  ## Ejecucion en IDE ##
1. **clonar repositorio desde Git**
2. **Importar como proyecto Maven**
3. **Actualizar Maven del Proyecto**
4. **Ejecutar**


## Ejecucion en Terminal ##
1. **Clonar repositorio desde Git  - Git Clone agregar-URL-Repo-GIT-AQUI** **En la Terminal de preferencia**
2. **Usar Terminal con comando**  **mvn clean compile install**
3. **Luego ejecutar con el comando**  **mvn spring-boot:run**
4. **Abrir Postman e Importar Archivos para Consumir el Api Rest**
5. **Seguir las instrucciones detalladas mas abajo**
6. **ENJOY** 


## Instrucciones Detalladas:

Instrucciones Detalladas:

- Cuando se inicie la aplicacion, se ejecutaron unos scripts (para crear tablas e insertar datos correspondientes) que se encuentran en la siguiente ruta del proyecto: src\main\resources\sql.

- Para revisar la base de datos H2, ingresa en http://localhost:8082/h2-console con las credenciales:
  Usuario: admin
  Contrase�a: admin123

  los datos estan en el archivo bd.properties.

- Instrucciones para probar con Postman, se debe Utilizar los archivos "Postman" de la carpeta PostManFiles para importar los Endpoints y variables de entorno. quedaran configurados los edp para su ejecucion. Recordar que antes de ejecutar algun endpoint debes loguear en el endpoint "Login" para obtener el token de seguridad. Favor Utiliza el siguiente "body" para el request:

`json`
- { "username": "GaboTest", "password": "usertest123" }

EL usuario esta en los scripts de carga inicial en la app

- Para Swagger, ingresar en http://localhost:8082/swagger-ui/index.html. Al igual que con Postman, Recordar que antes de ejecutar algun endpoint debes loguear en el endpoint "Login" para obtener el token de seguridad. Favor Utiliza el siguiente "body" para el request:
