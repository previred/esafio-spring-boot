# Gestor de Tareas
El presente desarrollo corresponde a una plataforma de gestión de tareas para mejorar la productividad de los equipos de trabajo de la empresa **Nuevo SpA**.
El sistema permite a los usuarios crear, actualizar, eliminar y listar tareas.

## Requerimientos técnicos
- Se utiliza Java 17 para su ejecución.
- Se debe contar con Maven para su compilación.
- La base de datos se crea temporalmente en memoria y se precargan datos de estados y usuarios.

## Indicaciones de uso
Ejecutar en consola:
```
mvn clean spring-boot:run
```
En el navegador se puede utilizar la documentación de Swagger:
```
http://localhost:8080/swagger-ui.html
```
Si fuera necesario revisar la base de datos generada, se debe acceder a:
```
http://localhost:8080/h2
```

Para utilizar la API, se incluyen las colecciones de Postema: Auth, Tareas y Usuarios. Se debe importar también las variables de ambientes.

Una vez creadas las colecciones, se debe iniciar por Auth para la creación del token. Este se mantendrá para todo el resto de colecciones, dado que se actualiza una variable de ambiente.

En los request que corresponda existirá un body de ejemplo en formato JSON. Todo estará también en la documentación Swagger antes indicada.