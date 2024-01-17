#Project Name
 
task-control 

#Description
 
Este es un micro-servicio rest para poder agregar, leer, actualizar y eliminar tareas para un usuario #Installation 

#Required

Java 17 version and Maven 3.9.5 

#Compilation and test ejecution
 
Correr 'mvn clean install' 

#Test endpoints 

Correr la aplicación como aplicación Spring boot y acceder a http://localhost:8080/swagger-ui/index.html para ejecutar solicitudes http a los distintos endpoints de la API 

#Endpoints 

#enpoint login 

	Este endpoint es para obtener el token de acceso jwt para acceder a los demás recursos (endpoints). 	IMPORTANTE: Se debe ejecutar este endpoint en primer lugar para obtener el token jwt de autorizacion para 	ejecutar los demás endpoints 

#Curl de prueba: 

curl -X 'GET'

'http://localhost:8080/task-control/login/Pepe%20Grillo/a2asfGfdfdf3'

-H 'accept: /' 

#endpoint add-task 

	Este endpoint es para agregar una nueva tarea a un usuario 

#Curl de prueba: 

curl -X 'POST'

'http://localhost:8080/task-control/add-task'

-H 'accept: /'

-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQZXBlIEdyaWxsbyxhMmFzZkdmZGZkZjMiLCJpYXQiOjE3MDU0NTQ2ODEsImV4cCI6MTcwNTU0MTA4MX0.NaKvjYFubj2JHu-nJJbip3wchrc8bFxP90PuRMwJP5s'

-H 'Content-Type: application/json'

-d '{ "nombre": "tarea 1", "descripcion": "descripción tarea 1", "idUsuario": 2 }' 

#enpoint read-task 

	Este endpoint es para obtener una tarea de un usuario 

#Curl de prueba:
 
curl -X 'GET'

'http://localhost:8080/task-control/read-task/1'

-H 'accept: /'

-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQZXBlIEdyaWxsbyxhMmFzZkdmZGZkZjMiLCJpYXQiOjE3MDU0NTQ2ODEsImV4cCI6MTcwNTU0MTA4MX0.NaKvjYFubj2JHu-nJJbip3wchrc8bFxP90PuRMwJP5s' 

#endpoint update-task
 
Este endpoint es para actualizar una tarea de un usuario 

#Curl de prueba: 

curl -X 'POST'

'http://localhost:8080/task-control/update-task'

-H 'accept: /'

-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQZXBlIEdyaWxsbyxhMmFzZkdmZGZkZjMiLCJpYXQiOjE3MDU0NTQ2ODEsImV4cCI6MTcwNTU0MTA4MX0.NaKvjYFubj2JHu-nJJbip3wchrc8bFxP90PuRMwJP5s'

-H 'Content-Type: application/json'

-d '{ "idTarea": 1, "nombre": "tarea 1", "descripcion": "Esta es la nueva descripcion", "idUsuario": 1, "idEstadoTarea": 2 }' 

#endpoint delete-task 

	Este endpoint es para eliminar una tarea de un usuario 

#Curl de prueba: 

curl -X 'POST'

'http://localhost:8080/task-control/delete-task'

-H 'accept: /'

-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQZXBlIEdyaWxsbyxhMmFzZkdmZGZkZjMiLCJpYXQiOjE3MDU0NTQ2ODEsImV4cCI6MTcwNTU0MTA4MX0.NaKvjYFubj2JHu-nJJbip3wchrc8bFxP90PuRMwJP5s'

-H 'Content-Type: application/json'

-d '{ "idTarea": 1 }'
