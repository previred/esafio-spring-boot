# Evaluación: JAVA

En este ejemplo, he utilizado H2 como base de datos en memoria y el resto de servicios web se exponen a través de Swagger-UI.
El proyecto se puede ejecutar de la siguiente manera:

 - mvn clean
 - mvn install
 - mvn spring-boot:run


 - Pre Requisitos

   Puedes usar la siguiente shell script para generar una clave secreta de 32 bytes de longitud y codificarla en Base64, keysecretgen.sh . Para ejecutar este script, guárdalo en un archivo, por ejemplo, generate_secret_key.sh, dale permisos de ejecución con chmod +x generate_secret_key.sh, y luego ejecútalo con ./generate_secret_key.sh.  
 
  - Dependencias io.jsonwebtoken 0.12.3   	
 
Una vez que el proyecto esté en funcionamiento, se puede acceder a DB, Services y Swagger-UI de la siguiente manera:

 - DB
	 - acceder a localhost:8080/h2-console con schema jdbc:h2:mem:testdb  conectarse y ver datos de tablas.
 
 
 - Services

	# REST API Endpoints
	
	- Endpoint:** `GET /usuarios/{id}`
	- Description:** Retrieves data for a user with the specified ID.
	- Authorization:** Bearer Token

 
	- Endpoint:** `GET /usuarios`
	- Description:** Retrieves data for all users.
	- Authorization:** Bearer Token

 -  Authentication Endpoints

	-Login
	- Endpoint:** `POST /auth/login`
	- Description:** Authenticates a user and returns a token.
	- Body:
	  ```json
	  {
	    "username": "example@email.com",
	    "password": "password"
	  }```



	 
 -  Register
 
	Endpoint: POST /auth/register
	Description: Registers a new user and returns a token.
	```
	{
	  "username": "example@email.com",
	  "password": "password",
	  "lastname": "Doe",
	  "firstname": "John",
	  "country": "USA"
	}```

 -  Login

To log in and retrieve a JWT token, send a POST request to the `/auth/login` endpoint with your username and password in the request body.

```bash
Endpoint: POST '/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "monica@gmail.com",
    "password": "password"
} 
```


 -  Update user Data
 
	Update User Data
	Endpoint: PUT /usuarios/{id}
	Description: Updates data for a user with the specified ID.
	Authorization: Bearer Token
	Body:
	```{
	  "username": "example@email.com",
	  "password": "newpassword",
	  "lastname": "Doe",
	  "firstname": "Jane",
	  "country": "USA"
	}```
	
 -  Delete User Data
 
	Endpoint: DELETE /usuarios/{id}
	Description: Deletes a user with the specified ID.
	Authorization: Bearer Token
	

		 
 - Swagger-UI
	 - localhost:8080/swagger-ui.html
