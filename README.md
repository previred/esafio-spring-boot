# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

El artefacto entregado proporciona todos los Endpoints necesarios para llevar a cabo operaciones CRUD en los datos vinculados a las tareas creadas por los usuarios.

## Consideraciones Generales

- Es necesario que el usuario se encuentre autenticado para realizar operaciones sobre la API
- La autenticacion se realiza mediante el endpoint **/auth/login** mencionado en el apartado de detalle tecnico.

## Instrucciones para instalacion y ejecucion

- Abrir proyecto en IDE de preferencia, se recomienda IntelliJ al poseer este una consola integrada.
- Una vez importado, ejecutar comando **mvn clean install** en la consola integrada para realizar compilacion e instalacion de artefacto en repositorio local.
- Finalizada la compilacion, ejecutar comando **./mvnw spring-boot:run** en la consola para iniciar la aplicacion. Se debe considerar como requisitos tener instalado Maven y JDK17 y creadas sus respectivas variables de ambiente.

## Detalle Tecnico API

- Es posible visualizar el contrato y especificaciones de la API visitando el siguiente enlace una vez que el servicio se encuentre en ejecucion:
  - http://localhost:8082/swagger-ui/index.html#/


- Todos los endpoints disponibles fueron agrupados en la collection de Postman adjunta en la carpeta */resources/collection*.

