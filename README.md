# Nombre: Felipe Castillo
# Correo: felipecastillosalgado@gmail.com
# Cargo: Ingeniero Desarrollo Backend

#Gestión de Tareas con Spring Boot y Java
Este proyecto proporciona una solución al desafío técnico propuesto por NUEVO SPA para desarrollar una plataforma de gestión de tareas destinada a mejorar la productividad de los equipos. Utilizando Spring Boot y Java, este sistema permite a los usuarios realizar operaciones CRUD sobre tareas, además de autenticarse mediante JWT.

#Software usado para desarrollo y pruebas
-IDE - Spring tool suit 4 (4.14.1)
-Insomnia - 8.6.1 (Postman ya no permite exportar e importar de forma gratuita)

#Ambientacion proyecto
1) Clona el repositorio en tu máquina local - comando "git clone url-del-proyecto"
2) Ejecutar la descarga de dependecias
3) Levanta el servidor local en puerto 8080

#Pasos para probar los servicios:
1) Descarga el archivo para_pruebas.json ubicado en la ruta src/main/resources/json
2) Importa el archivo para_pruebas.json en el programa Insomnia - 8.6.1
3) Ejecutar el servicio (Login) con las credenciales felipeA/12345678A, el servicio debe retornar un token con el que se ejecutaran los endpoint del CRUD de tareas
4) Existe otro enpoint para registrar un usuario (Register) en caso se requiera probar con otro usuario
5) Ejecutar los endpoint de CRUD de tareas con la opción Bearer token

#Datos pre cargados:
#Usuario: 
Id - username - password
1  - felipeA  - 12345678A

#Estado tareas: 
Id - Name
1	 Pendiente
2	 Completado

#Urls útiles para cuando se tiene el servicio arriba:
-Base de datos:http://localhost:8080/h2-console/
Credenciales base de datos: sa/(vacio)
-Documentación de la API utilizando OpenAPI y Swagger: http://localhost:8080/swagger-ui/index.html#/
