
---

## Pasos para Configurar y Ejecutar la API

### Ejecución en IDE
1. **Clonar el repositorio desde Git**:
    - Utiliza tu cliente Git favorito para clonar el repositorio.
2. **Importar como proyecto Maven**:
    - Abre tu IDE (como IntelliJ IDEA o Eclipse) e importa el proyecto como un proyecto Maven.
3. **Actualizar Maven**:
    - Asegúrate de actualizar las dependencias Maven del proyecto.
4. **Ejecutar**:
    - Inicia la aplicación desde tu IDE ejecutando la clase principal (`main`).

### Ejecución en Terminal
1. **Clonar el repositorio**:
    - Ejecuta el siguiente comando en tu terminal:
      ```bash
      git clone https://github.com/JackBlazzers/desafio-spring-boot.git
      ```
2. **Compilar y construir el proyecto**:
    - Usa el siguiente comando para limpiar, compilar e instalar las dependencias:
      ```bash
      mvn clean compile install
      ```
3. **Ejecutar la aplicación**:
    - Inicia la aplicación con el siguiente comando:
      ```bash
      mvn spring-boot:run
      ```

---

## Probando la API REST

### Revisar la base de datos H2
1. **Acceso a la consola H2**:
    - Abre en tu navegador: [http://localhost:8082/h2](http://localhost:8082/h2).
2. **Credenciales**:
    - Usuario: `sa`
    - Contraseña: `pass`.
3. **Scripts de inicialización**:
    - Los scripts para crear tablas e insertar datos iniciales están en:
      ```
      src/main/resources
      ```
4. **Archivo de configuración**:
    - Detalles adicionales de la base de datos se encuentran en:
      ```
      src/main/resources/application.yaml
      ```

### Usar Postman
1. **Importar colección de Postman**:
    - Importa el archivo:
      ```
      Desafio-java.postman_collection.json
      ```
    - Esto configurará automáticamente los endpoints y las variables de entorno necesarias.
2. **Autenticación**:
    - Antes de usar cualquier endpoint, debes autenticarte en el endpoint de login:
      ```
      POST /api/login
      ```
    - Usa el siguiente `body` para obtener el token de seguridad:
      ```json
      {
        "username": "user1",
        "password": "1234"
      }
      ```
    - El usuario `user1` y su contraseña están configurados en los scripts de inicialización.

### Usar Swagger
1. **Acceso a Swagger**:
    - Abre en tu navegador: [http://localhost:8082/actuator/swagger-ui/index.html#/](http://localhost:8082/actuator/swagger-ui/index.html#/).
2. **Autenticación**:
    - Igual que en Postman, antes de ejecutar cualquier endpoint, autentícate en el endpoint de login:
      ```
      POST /api/login
      ```
    - Usa el siguiente `body` para obtener el token de seguridad:
      ```json
      {
        "username": "user1",
        "password": "1234"
      }
      ```

---
