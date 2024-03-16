
##Pre requisitos
- Tener bash de Maven instalado (comando mvn)
- Tener java 17 instalado


##Pasos para la ejecución

1) descargar el proyecto  
2) abrir una terminal dentro de la raíz del proyecto (cd ubicaciondelproyecto)  
3) ejecutar: mvn compile 
4) ejecutar: mvn exec:java -Dexec.mainClass=cl.nuevospa.OpenApiGeneratorApplication  
5) Para poder ver lo documentado en swagger pedirá usuario y contraseña.   
	username: admin  
	password: password  

6) Para llegar a los endpoints desde postman se adjunta collection desafio-nuevaspa.postman_collection.json en la raiz del proyecto  
7) Agregar como header Authorization el Bearer token que se genera utilizando el request "login for requests" que está en postman  
8) Sólo el API del CRUD de tareas fue generado openapi generator  
 




Comentarios personales adicionales:  
		No había utilizado Open Api Generator y lo aprendí sobre la marcha en este día y medio de desarollo, por lo que pretendí seguir los principios de arquitectura limpia y principios SOLID junto al aprendizaje de la nueva herramienta, cualquier duda sugiero preguntarme.