# Manual Usuario Desafío

## Pasos:

1) Clonar proyecto desde https://github.com/previred/desafio-spring-boot
2) Correr el proyecto (Se utilizó Intelijj) 
3) Importar en postman el archivo desafio.postman_collection, 
que viene dentro del proyecto.
4) Ingresar a modo de prueba al request listar-usuarios, no mostrará nada, 
pero responderá bien.
4) Ingresar al request registrar-usuario, el body viene cargado 
para crear un usuario.
5) Ingresar nuevamente a listar-usuarios, ahora se podrá ver la lista de usuarios.
6) Ingresar a modo de prueba al request listar-tareas, no mostrará nada, 
pero será por un error de autorización. (No tiene permisos para ver acceder a 
este endpoint)
7) Ingresar al request login, si ingresa mal las credenciales arrojará un error,
si están bien genera un Bearer token el cual permitirá ingresar a las rutas que 
no estén permitidas.
8) Ahora podrá ingresar al CRUD de tareas, para eso debe copiar el Bearer token
generado en el login, dentro de postman, en cualquiera de los endpoints de
tarea, en authorization(cambiar type a Bearer token).

