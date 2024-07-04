# Bienvenid@s!
Esta es mi prueba tecnica para la Postulación Dev. Backend Java, para probar esta aplicacion se requiere tener instalado:
***
        1 - Java 17
        2 - Maven
***
Sigue los siguiente pasos:
***
    1 - Ubicarse dentro de la capeta raiz /demo y abrir la ventana de consola de maven
    2 - Ejecutar el siguiente comando mvn spring-boot:run
***
Despues con la aplicacion de postman probar las api:
***
    1 - Realizar como primera una peticion POST a la siguiente URI para obtener el token: http://localhost:8082/login e ingresar en el body lo siguiente (esto retorna un token):
***
        {
            "username":"admin",
            "email": "admin@gmail.com",
            "password":"123456"
        }

        Este API, retorna la siguiente estructura:
        {
            "Message": "Autenticacion Correcta",
            "Username": "admin",
            "token": "XXXXX"
        }
***  
    
    2 - El token retornado en la anterior api se debe ingresar como Authorization de tipo Bearer Token en las siguientes api 
        * Crear una nueva tarea

            TIPO DE PETICION:   POST

            URI:                http://localhost:8082/api/task  

            BODY:
                                {
                                    "name": "Nueva tarea",
                                    "codigoStatus": "BR"
                                }
            AUTHORIZATION TYPE: BEARER TOKEN (ingresar el token que retorno en la primera peticion) 
        
        * Listas las tareas creadas

            TIPO DE PETICION:   GET

            URI:                http://localhost:8082/api/task  

            AUTHORIZATION TYPE: BEARER TOKEN (ingresar el token que retorno en la primera peticion)

        * Buscar tarea segun id

            TIPO DE PETICION:   GET

            URI:                http://localhost:8082/api/task/{id} (el {id} es el id del registo a buscar)

            AUTHORIZATION TYPE: BEARER TOKEN (ingresar el token que retorno en la primera peticion)

        * Actualizar tarea segun id

            TIPO DE PETICION:   PUT

            URI:                http://localhost:8082/api/task/{id} (el {id} es el id del registo a actualizar) 

            BODY:
                                {
                                    "name": "Actualiando el nombre de la tarea",
                                    "codigoStatus": "BR"
                                }
            AUTHORIZATION TYPE: BEARER TOKEN (ingresar el token que retorno en la primera peticion) 


        * Eliminar tarea segun id

            TIPO DE PETICION:   DELETE

            URI:                http://localhost:8082/api/task/{id} (el {id} es el id del registo a eliminar)

            AUTHORIZATION TYPE: BEARER TOKEN (ingresar el token que retorno en la primera peticion)

     
