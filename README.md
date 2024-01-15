# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.


### Documentación:
- La data en BD se carga desde la clase Extra, la cual implementa la interface InitializingBean.
- Un usuario cargado por esta clase seria: username: user1, password: user1
- Los estados de las tareas que acepta la aplicacion son: CREATED, ATTENDED (CREATED es una valor por defecto, asi que al crear se puede obviar)
- La ruta del Swagger es la de por defecto: /swagger-ui/index.html#/ 
- La collection de Postman esta adjuntada.
