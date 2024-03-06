# API Tareas para Nuevo SPA

Este documento explicará como funciona la API de Tareas, tanto en funcionalidad como en componentes e instalación.

## Funcionamiento:
- Hay una carga inicial de datos que carga usuarios (admin, usuario1).
- Existe un endpoint para realizar login y que devuelve un Token JWT con el cual se permite el uso de todos los demas endpoint de la API. Es la unica ruta publica de la API.
- Dicho token servirá para asociar el usuario a todas las funcionalidades de tareas.
- Existen endpoint para consultar usuarios registrados en el sistema.
- El endpoint para crear tareas (POST) crea tareas asociadas al usuario del token JWT que se esta usando.
- El endpoint para consultar tareas (GET) busca tareas asociadas al usuario dueño del token utilizado para la petición.
- Los endpoint para eliminar y modificar (DELETE y PUT) solo aplican la funcionalidad a tareas asociadas al usuario dueño del token utilizado al momento de la petición.


## Como hacerlo funcionar:
### Visual Studio Code:
- Abrir la carpeta del proyecto con Visual Studio Code.
- Instalar las extensiones:
    * https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack
    * https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack

- Desde el "Spring Boot Dashboard" en la sección "APPS" ejecutar el boton "run" de la aplicación "nuevospa"
- Como resultado la aplicación queda corriendo en:

    http://localhost:8081

### Usar Swagger UI:
Se integró Swagger UI para poder ver la documentación y usar la API desde la Web.
Una vez ejecutada la API se debe dirigir a: 

http://localhost:8081/swagger-ui/index.html 

### Postman:
Se creó un archivo que contiene un Postman Collection que esta en la raiz del proyecto llamado "API Tareas.postman_collection.json" el cual se puede importar y contiene instrucciones.
