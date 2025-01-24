# Nombre de la Aplicación

Descripción breve de la aplicación.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- [Java JDK 17 o superior](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## Clonar el Repositorio

dirigirse a la raiz del proyecto y ejecutar
```bash
mvn clean install
```
## Clonar el Repositorio

Para ejecutar la aplicación, usa el siguiente comando Maven:


```bash
mvn spring-boot:run
```

## End points

```bash
http://localhost:8080/auth/addNewUser
http://localhost:8080/auth/generateToken
http://localhost:8080/api/tareas
http://localhost:8080/api/tareas/{id}

```