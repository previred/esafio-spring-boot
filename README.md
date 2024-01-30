# Spring Boot API de gestión de tareas y usuarios

Esta es una simple API para registrar usuarios y tareas, construida con Spring Boot e incluye Swagger UI para la prueba y documentación.

## <u>Prerrequisitos</u>
* ![Java](https://img.shields.io/badge/Java-17%2B-brightgreen)
* ![Maven](https://img.shields.io/badge/Maven-3%2B-blue)

## <u>Documentación</u>
La documentación de la API se encuentra [aquí](http://localhost:9090/swagger-ui/). Para generar la documentación en formato JSON, puedes descargarla [aquí](http://localhost:9090/v3/api-docs).

## <u>Ejecutando la API</u>
1. Clona el [repositorio](https://github.com/camilitwo/desafio-spring-boot_camilo-g):
    ```shell
    git clone https://github.com/camilitwo/desafio-spring-boot_camilo-g
    ```

2. Ejecuta la API usando Maven:
    ```shell
    mvn spring-boot:run
    ```

## <u>Probando la API</u>
Para probar la API, utiliza Swagger UI, disponible [aquí](http://localhost:9090/swagger-ui/). Desde allí, puedes ver una lista de endpoints finales disponibles y probarlos.


## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST para obtener un token de acceso:

```shell
curl --location 'http://localhost:9090/api/v1/login' \
--header 'Content-Type: application/json' \
--data '{
    "password": "contraseñasegura",
    "username": "camilo"
}'
```

## Ejemplo de respuesta
####
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYW1pbG8iLCJleHAiOjE3MDY2MzQxNDEsImlhdCI6MTcwNjYyMzM0MX0.1W5M8xpHbCuU4kNl5h3L9SAxEZ0vULx6ZWFRFdeNCao"
}
```

# <u>/users</u>
## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST para guardar un usuario en la base de datos:

```shell
curl --location 'http://localhost:9090/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYW1pbG8iLCJleHAiOjE3MDY2MzQxNDEsImlhdCI6MTcwNjYyMzM0MX0.1W5M8xpHbCuU4kNl5h3L9SAxEZ0vULx6ZWFRFdeNCao' \
--data '{
    "email": "prueba@mail.cl",
    "pass": "contraseñaprueba",
    "tareas": [
        {
            "createdAt": "2024-01-29T20:50:59.629Z",
            "estadoTarea": {
                "id": 1
            }
        }
    ],
    "username": "prueba"
}'
```

## Ejemplo de respuesta
####
```json
{
  "id": 2,
  "username": "prueba",
  "email": "prueba@mail.cl"
}
```

# <u>/tareas</u>
## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST para guardar una tarea en la base de datos:

```shell
curl --location 'http://localhost:9090/api/v1/tasks' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYW1pbG8iLCJleHAiOjE3MDY2MzQxNDEsImlhdCI6MTcwNjYyMzM0MX0.1W5M8xpHbCuU4kNl5h3L9SAxEZ0vULx6ZWFRFdeNCao'
```

## Ejemplo de respuesta
####
```json
[
  {
    "id": 1,
    "estado": "En ejecución",
    "fecha": "2024-01-30T14:04:22.070+00:00",
    "usuario": {
      "id": 1,
      "username": "camiloprueba",
      "email": "Prueba 2"
    }
  },
  {
    "id": 2,
    "estado": "En ejecución",
    "fecha": "2024-01-30T14:05:31.401+00:00",
    "usuario": {
      "id": 2,
      "username": "prueba",
      "email": "prueba@mail.cl"
    }
  }
]
```


# <u>Script de base de datos</u>
> Los scripts de base de datos los encuentras [acá](src/main/resources/data.sql)




Proyecto realizado por [<u>__Camilo Gonzalez__</u>](https://www.linkedin.com/in/camilo-gonzalez-villalobos-2ba062a4/)

[![GitHub Profile](https://img.shields.io/badge/GitHub-camilitwo-green?style=flat&logo=github)](https://github.com/camilitwo)
