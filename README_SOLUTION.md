# Solución desafio técnico By Roberto Olavarria

## Scaffolding 

La distribución del proyecto es bastante sencilla y clara de leer, sin embargo agrego una breve descripción.

Está basado en una arquitectura de N-capas simple y básica, principalmente se concentra todo en 3 packages ***controller***, ***repository*** y ***service***

## Compilacion & Start

Antes de iniciar la aplicación de manera local será necesario descargar las dependencias (sólo se uso lo requerido por el desafío)

`mvn clean install
`
una vez compilada la aplicación, estando en la raíz del proyecto ejecutar:

`java -jar target/moveapps-desafio.jar 
`

## Base de datos

Tal como proponía el desafío la app está conectada a una bbdd h2 en memoria
para la conexión a esta misma se puede hacer con el siguiente link (en local)

[consola h2](http://localhost:8080/h2-console)

```
user: sa
url: jdbc:h2:mem:moveappsdb
```

no necesita password.

Una vez dentro de la consola se pueden verificar las tablas y datos pre-cargados
cuyos archivos de origen están en `src/main/resources/data`

## Swagger y documentación api
No agregué ninguna anotación extra de openapi, tampoco utilicé alguna estrategía como API-First ya que en mi parecer están fuera del scope del desafío.
Sin embargo sí agregué la librería para la auto generación del swagger el cual se puede ver en el siguiente link:

[swagger](http://localhost:8080/swagger-ui/index.html)

## Security
La aplicación está securizada con Spring Security utilizando JWT tal como lo pedía el desafío.
Se hizo uso de la tabla ***usuarios*** para realizar la verificación de identidad


## Usabilidad
En los resources (***src/main/resources/postman***) se encuentra un colección con todos los endpoints y un ejemplo de uso utilizando los datos de prueba pre-cargados.
Este mismo realiza la petición del token y posterior asignación a la request.


