# Proyecto Nuevo Spa

El proyecto se puede ejecutar de la siguiente forma:

```
mvn spring-boot:run
```

O bien, mediante Docker:

```
docker build -t "nuevo-spa" .
docker run -d -it -p 8080:8080 --name=nuevo-spa nuevo-spa

docker logs -f 501cbba5a6fc

```
A su vez, dejo en resources el postman collection.
```
RetoMoveApps.postman_collection
```

JSON:

OBTENER JWT - SEGURIDAD:
```
{
    "user": "kevin",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoia2V2aW4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzA2NzY1MDE0LCJleHAiOjE3MDY3NjU2MTR9.3umMDym4rt8YOZmjyUgD2UvumQh6wC-FTPcCajMpGFcr0kUDdUjWoLkIP30gjh9z4t6siO90Ll98TpSzy-n31w"
}
```
OBTENER TAREAS:
```
[
    {
        "id": 1,
        "title": "CEPILLADO",
        "detail": "CEPILLADO - 5PM - AGENDADO",
        "status": "PENDIENTE"
    },
    {
        "id": 2,
        "title": "TINTE",
        "detail": "TINTE PARA CABELLO - 6PM",
        "status": "COMPLETADO"
    },
    {
        "id": 3,
        "title": "CORTE",
        "detail": "CORTE REGULAR - HOMBRE - 3PM",
        "status": "ACTIVO"
    }
]
```
ELIMINAR TAREA:
```
204 NO CONTENT
```
CREAR TAREA:
```
REQUEST:

    {
        "title": "CORTE VARON",
        "detail": "CORTE - 12AM - AGENDADO",
        "status": 1
    }

RESPONSE: 

    {
        "id": 4
    }

```
ACTUALIZAR TAREA:
```
REQUEST:

    {
        "title": "CEPILLADO",
        "detail": "CEPILLADO - 5PM - AGENDADO",
        "status": 2
    }

RESPONSE:
    
    {
        "id": 4
    }
```

GRACIAS. :)