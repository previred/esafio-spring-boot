<!-- PROJECT LOGO -->
<br />
<p align="center">

<h3 align="center">Desafio Spring Open Api Generator, JWT, API, JPA</h3>

  <!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Contenido</summary>
  <ol>
    <li>
      <a href="#CONFIGURACIÓN">Configuración</a>
      <ul>
        <li><a href="#Configuración-DB">Base de datos</a></li>
      </ul>
      <ul>
        <li><a href="#Configuración-Properties">application.properties</a></li>
      </ul>
      <ul>
        <li><a href="#Configuración-Postman">Postman</a></li>
      </ul>
    </li>
    <li>
      <a href="#SWAGGER">Swagger Path:</a>
    </li>
  </ol>
</details>

## CONFIGURACIÓN
<!-- Configuración DB-->
## Configuración DB
Para configurar la base de datos es necesario ejecutar el comando de H2:
<code>RUNSCRIPT FROM 'db.sql'</code>
utilizando el archivo db.sql de este repositorio.
Si no se usa H2, el archivo db.sql contiene las queries necesarias y en orden para construir las tablas.

<!-- Configuración application.properties-->
## Configuración Properties
Es necesario configurar el application.properties del proyecto con la configuración de la DB de H2:

<b>spring.datasource.url</b>=Path de conexión

<b>spring.datasource.username</b>=Usuario de la Base de datos.

<b>spring.datasource.password</b>=Contraseña del usuario de la Base de datos.

<!-- Configuración Postman-->
## Configuración Postman
Para obtener los endpoints con sus ejemplos de body y header es necesario importar el archivo <b>Prueba Tecnica Tecnova.postman_collection.json</b> a POSTMAN.


## SWAGGER
## Swagger Path:
/pruebaTecnica/v1/swagger-ui/index.html

</p>
