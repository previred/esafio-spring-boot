**********************************************************************************************************************************************************************************

# Pasos para Configurar y Ejecutar la API

1. **Obtener el código fuente desde Git**
2. **Importar como proyecto Maven**
3. **Actualizar Maven del Proyecto**
4. **Ejecutar**

## Instrucciones adicionales:

Instrucciones adicionales:

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
