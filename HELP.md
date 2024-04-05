# MsCoreNuevoSPA

### Alcance

Se trabajó bajo la especificación de open api y como lineamiento se desarrolló bajo api first.
La especificación se encuentra en `docs/openapi.yml` y se puede hacer visible desde el editor en línea https://editor-next.swagger.io/
Adicional la aplicación se encuentra construida con `Spring Boot 2.7.14` y `Java 17`

### Compilación y ejecución

Para generar el artefacto (.jar) y ejecutar se requiere ejecutar los siguientes comandos.

```shell
# Locate home directory folder
  ./mvnw clean install
  ./mvnw spring-boot:run
  ./mvnw spring-boot:run
```

### Pruebas.

Para lograr una correcta ejecucion de las diferentes apis es necesario exportar la collection de Postman
`docs/qa/Moveapps-TestNuevoSPA.postman_collection.json` alli se encuentra el desglose de las diferentes operaciones según la entidad de
negocio.

- Se requiere primero ejecutar el api de autenticación ubicada como "Generate Authentication", se encuentra habilitados los siguientes
  usuarios por defecto.

  | Username                  | Password |
        |---------------------------|----------|
  | fermolina@yopmail.com     | 123      |
  | testing@yopmail.com       | 123456   |
  | camisacristan@yopmail.com | 123      |

- Esta api generar un JWT que debera ser utilizado para poder usar el resto de apis disponibles, para que esto ocurre. Se requiere agregar
  el token en el header `Authorization` acompañado del prefijo `Bearer`.
  Ejemplo `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MTIzMzQzNTEsImV4cCI6MTc0Mzg3MDM1MSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.VN1l8h1-vd0WfT9qMZ3Pih-mUj1YB3uxhW9aWXpc8Os`

- Para finalizar tener en cuenta el swagger donde se describen los parámetros requeridos para llegar acabo una ejecución exitosa.