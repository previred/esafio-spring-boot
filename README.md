IGNACIO BELTRAN SILVA

La colección postman viene dentro de la carpeta resources.
El puerto por defecto y la colección están pensadas para el puerto 8080
La url para acceder a swagger es: http://localhost:8080/swagger-ui.html

Como toda aplicación, esta es perfectible y hay algunas reglas de negocio que por tiempo no las pude incluir.
La base de datos h2, viene pre-cargada (import.sql en resources) con solo los estados de las tareas. 
Es importante seguir paso por paso de la collection, la creación de usuario y login. 
Es vital crear un nuevo environment de postman, para que se puedan cargar los jwt de autenticación, el script está listo
para no tener que estar copiando manualmente el jwt para los endpoint protegidos. 

Los roles de usuario posibles son ROLE_ADMIN y ROLE_USER, 
cada controller tiene ciertas limitaciones o no para el rol de cada usuario.


