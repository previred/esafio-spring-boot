# JSON DE PRUEBA --POSTMAN

* Login
- Endpoint Metodo POST :  localhost:8085/authenticate/login
{
    "username": "previred",
    "password": "admin"
}

# Con el token obtenido ir a pestaña de autorizacion y seleccionar el tipo Bearer token. se adunta imagen ![img.png](img.png)

* Crear tarea y asignar a un usuario.

- Endpoint Metodo POST : localhost:8085/api/tareas/save

{
    "descripcion": "Primera tarea de prueba",
    "estadoTarea": {
        "id": 2,
        "descripcion": "ASIGNADA"
    },
    "usuario": {
        "id": 1,
        "username": "cmartinez",
        "email": "cmartinez@gmail.com",
        "password": "$2a$10$grht1iEFzAgPG87sWwG.7OfmRYKyThWizvAbSX0hy033d4KPMRXjm",
        "role": "USER"
    }
}

{
    "descripcion": "Segunda tarea de prueba",
    "estadoTarea": {
        "id": 2,
        "descripcion": "ASIGNADA"
    },
    "usuario": {
        "id": 2,
        "username": "mlopez",
        "email": "mlopez@gmail.com",
        "password": "$2a$10$AIMTpuZ7sU4WFFUKQMdyCuvUWZ3LEjijhdABYOL8xGeY2gACStTo6",
        "role": "USER"
    }
}



# Con el token obtenido (Anteriormente) ir a pestaña de autorizacion y seleccionar el tipo Bearer token.

* Modificar tarea.

- Endpoint metodo POST : localhost:8085/api/tareas/update

{
    "id": 2,
    "descripcion": "Modificacion tarea de prueba",
    "estadoTarea": {
        "id": 3,
        "descripcion": "EN_PROCESO"
        },
    "usuario": {
        "id": 1,
        "username": "cmartinez",
        "email": "cmartinez@gmail.com",
        "password": "$2a$10$grht1iEFzAgPG87sWwG.7OfmRYKyThWizvAbSX0hy033d4KPMRXjm",
        "role": "USER"
    }
}

# Con el token obtenido (Anteriormente) ir a pestaña de autorizacion y seleccionar el tipo Bearer token.

* Listar todas las tareas .
- Endpoint metodo GET : localhost:8085/api/tareas/list


# Con el token obtenido (Anteriormente) ir a pestaña de autorizacion y seleccionar el tipo Bearer token.

* Listar todas las tareas de un usuario .
- Endpoint metodo GET : localhost:8085/api/tareas/list-tarea-by-id/1

# Con el token obtenido (Anteriormente) ir a pestaña de autorizacion y seleccionar el tipo Bearer token.

* Obtener una tarea por id .
- Endpoint metodo GET : localhost:8085/api/tareas/1


# Con el token obtenido (Anteriormente) ir a pestaña de autorizacion y seleccionar el tipo Bearer token.

* Eliminar una tarea por id .
- Endpoint metodo DELETE : localhost:8085/api/tareas/1