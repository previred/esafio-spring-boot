# Gestión de Tareas API

Esta es una aplicación API RESTful desarrollada en Spring Boot para gestionar usuarios y tareas. La aplicación incluye autenticación basada en JWT, un CRUD completo de tareas y está documentada con OpenAPI/Swagger. También utiliza una base de datos H2 para almacenamiento.

---

## **Requisitos Previos**

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes requisitos:

- **Java**: JDK 17 o superior
- **Maven**: 3.6 o superior
- **Postman** (opcional, para probar la API)
- **Navegador Web** (para acceder a Swagger y la consola H2)

---

## **Instrucciones para Ejecutar la Aplicación**

1. **Clonar el Repositorio**
```bash
   git clone <URL_DEL_REPOSITORIO>
   cd gestion-tareas
```
2. **Compilar y construir el proyecto**

    Ejecuta en la terminal
```bash
    mvn clean compile install
```
3. **Ejecutar Aplicación**

    Ejecuta en la terminal
```bash
    mvn spring-boot:run
```

## Instrucciones

## ** Cargar Datos Automaticamente
Al iniciar la aplicación, el componente DataLoader crea y precarga los siguientes datos:

Usuario por defecto:
- Username: admin
- Password: Secret1234
- Email: admin@gestion.com

Estados de las Tareas:
- Pendiente
- En Progreso
- Completada

## **Acceso a la Consola H2
- Abre un navegador y ve a: http://localhost:8081/h2-console
- Ingresa las siguientes credenciales:
- JDBC URL: jdbc:h2:mem:gestiondb
- User Name: admin
- Password: secret1234
- Haz clic en "Connect".
Podrás explorar las tablas usuarios, tareas y estados_tarea.

## **Probar API con Postman
Dentro del proyecto hay una carpeta llamada postman que contiene un archivo de configuración para Postman.

Pasos para Probar con Postman:
- Abre Postman.
- Haz clic en el botón "Import" en la parte superior izquierda.
- Selecciona el archivo postman/Desafio Backend Previred.postman_collection.json.
- Una vez importado, encontrarás los endpoints organizados.
- Antes de ejecutar cualquier endpoint protegido:
    Usa el endpoint "Login" para obtener un token JWT.

Request Body para el login:

```json
    {
        "username": "admin",
        "password": "Secret1234"
    }
```
Copia el token y configúralo como Bearer <TOKEN> en los headers para los siguientes endpoints.
Los endpoints estarán listos para pruebas.

## **Swagger UI
La API está documentada con Swagger. Swagger UI te permite explorar y probar los endpoints de manera interactiva.

Pasos para Usar Swagger UI:
- Abre un navegador y ve a: http://localhost:8081/swagger-ui/index.html
- Haz clic en los endpoints para ver detalles como:
    Método HTTP, parámetros, respuestas esperadas.
- Antes de ejecutar cualquier endpoint protegido:
    Usa el endpoint "Login" en Swagger para obtener un token JWT.

Request Body para el login:
```json
    {
        "username": "admin",
        "password": "Secret1234"
    }
```


### Notas
#### JWT Token:

Al autenticarte correctamente, obtendrás un token JWT.
Usa este token en el encabezado Authorization con el formato Bearer <TOKEN> para acceder a los endpoints protegidos.

#### Base de Datos Volátil:

Como la base de datos H2 es en memoria, los datos se perderán al detener la aplicación. Para mantener los datos, deberás configurar una base de datos persistente.
