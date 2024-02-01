# desafio-spring-boot

Desarrollada con:

- [Spring Boot 2.7.18](http://projects.spring.io/spring-boot/)
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Ejecutando la aplicación localmente

Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local. Una forma es ejecutar el método `main` en la clase `com.desafio.spring.desafiospringboot.DesafioSpringBootApplication` desde su IDE.

Alternativamente, puede usar el [complemento Spring Boot Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) así:

```shell
mvn spring-boot:run
```

## Documentación de la API

Luego de ejecutar y levantar la API localmente, revisamos los servicios de nuestra API en la siguiente url en su navegador:

```shell
http://localhost:8180/swagger-ui.html
```

## Porbando nuestros servicios

Para probar nuestros servicios, debemos tener la aplicacion [Postman](https://www.postman.com/downloads/) en nuestro computador.

Luego, descargamos el archivo `desafio-spring.json` que esta en los recursos de nuestro proyecto `desafio-spring-boot/src/main/resources`.

Abrimos [Postman](https://www.postman.com/downloads/) e importamos nuestro archivo `desafio-spring.json`.


![Alt text](img/import.jpg?raw=true "Import")


Para iniciar debemos autenticarnos en el servicio Login con los siguientes datos:

```shell
{
  "userName": "admin",
  "password": "admin"
}
```

Para todos los demas servicios, debemos ocupar el token retornado en el servicio `login` y agregarlo en la pestaña `Authorization` y presionar en `Bearer Token`


![Alt text](img/token.jpg?raw=true "Import") 


Para consumir los demas servicios en [Postman](https://www.postman.com/downloads/) debe guiarse de la Documentación de la API descrita mas arriba. 

## desafio-spring-boot
