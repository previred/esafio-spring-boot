# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.

## Objetivo:
Crear una API RESTful utilizando Spring Boot que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## como ejecutar la aplicación

### En VS Code
Para ejecutar con las extensiones de Spring boot de VS Code, simplemente instale las debidas extensiones (Spring boot extension pack y el Spring boot dashboard), acceda al spring boot dashboard en la barra de navegación de la izquierda en VS Code, y de click a ejecutar en la app de desafio-spring-boot

### Línea de comandos (Windows)
Para ejecutar desde la línea de comandos, con maven instalado en el equipo, corra la aplicación con el comando: mvn spring-boot:run

### Con el JAR
Para ejecutar la app con el archivo JAR, debe correr por línea de comandos lo siguiente: 
```java -jar .\desafio-spring-boot-0.0.1-SNAPSHOT.jar```

## Probar las peticiones
Una vez levantada la API, se debe importar el archivo de collection de postman ubicado en la raíz del proyecto (Desafio Spring Boot.postman_collection.json) y probar las peticiones según se muestran en el archivo swagger, están todas preparadas igualmente para ejecutar. 
Debe seguir los siguientes pasos:

1 Ejecutar el POST login. Usuarios de prueba son:
- Usuario 1
    - nombreUsuario: arturovicencio
    - Contraseña: secret
- Usuario 2
    - nombreUsuario: usuario_prueba
    - Contraseña: secret

2 Ejecutar cualquier otra petición de prueba, preferentemente, crear tareas mediante POST tarea. El access token se guardará automáticamente como una variable de entorno y se usará en las demás peticiones a la API